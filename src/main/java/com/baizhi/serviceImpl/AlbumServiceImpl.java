package com.baizhi.serviceImpl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> albumFindByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        /** total  总页数
         * page   当前页
         * records 总条数
         *
         **/
        Integer start = (page - 1) * rows;
        List<Album> albums = albumMapper.albumFindByPage(start, rows);

        Integer count = albumMapper.count();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;

        map.put("rows", albums);
        map.put("records", count);
        map.put("total", total);
        map.put("page", page);
        return map;
    }

    @Override
    public String save(Album album) {
        String s = UUID.randomUUID().toString();
        album.setId(s);
        album.setCount(0);
        albumMapper.save(album);
        return s;
    }

    @Override
    public void updatePath(String id, String newPath) {
        albumMapper.updatePath(id, newPath);
    }

    @Override
    public void update(Album album) {
        albumMapper.update(album);
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            albumMapper.deleteAllChapter(id);
            albumMapper.delete(id);
        }
    }
}
