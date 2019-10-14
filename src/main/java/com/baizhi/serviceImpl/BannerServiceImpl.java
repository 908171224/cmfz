package com.baizhi.serviceImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        /** total  总页数
         * page   当前页
         * records 总条数
         *
         **/
        Integer start = (page - 1) * rows;
        List<Banner> banners = bannerMapper.findByPage(start, rows);

        Integer count = bannerMapper.count();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;

        map.put("rows", banners);
        map.put("records", count);
        map.put("total", total);
        map.put("page", page);
        return map;
    }

    @Override
    public String save(Banner banner) {
        String s = UUID.randomUUID().toString();
        banner.setId(s);
        banner.setCreateDate(new Date());
        bannerMapper.save(banner);
        return s;
    }

    @Override
    public void updatePath(String bannerId, String newPath) {
        bannerMapper.updatePath(bannerId, newPath);
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.update(banner);
    }

    @Override
    public void deleteById(String[] ids) {
        for (String id : ids) {
            bannerMapper.deleteById(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Banner> findAll(HttpServletResponse response) throws IOException {
        List<Banner> banners = bannerMapper.findAll();

        for (Banner banner : banners) {
            String imgPath = banner.getImgPath();
            String s = "D:\\source\\project\\cmfz\\src\\main\\webapp\\img\\" + imgPath;
            banner.setImgPath(s);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图列表", "table"), Banner.class, banners);
        //workbook.write(new FileOutputStream(new File("D:/百知培训安装包/easy.xls")));

        String encode = URLEncoder.encode("轮播图信息.xls", "UTF-8");//响应头
        response.setHeader("content-disposition", "attachment;filename=" + encode);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        return null;
    }
}
