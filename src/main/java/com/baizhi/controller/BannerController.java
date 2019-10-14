package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        Map<String, Object> map = bannerService.findByPage(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public String edit(Banner banner, String oper, String[] id) {
        if (oper.equals("add")) {
            String s = bannerService.save(banner);
            return s;
        } else if (oper.equals("del")) {
            bannerService.deleteById(id);
        } else {
            bannerService.update(banner);
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile imgPath, String bannerId, HttpSession session) {
        //获取图片存储位置
        String realPath = session.getServletContext().getRealPath("/img");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String originalFilename = imgPath.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            imgPath.transferTo(new File(realPath, newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bannerService.updatePath(bannerId, newFileName);
    }

    @RequestMapping("table")
    public void findAll(HttpServletResponse response) throws IOException {
        List<Banner> banners = bannerService.findAll(response);
    }
}
