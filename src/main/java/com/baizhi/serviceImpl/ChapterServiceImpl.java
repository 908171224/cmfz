package com.baizhi.serviceImpl;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> chapterFindByPage(Integer page, Integer rows, String albumId) {
        Map<String, Object> map = new HashMap<>();
        /** total  总页数
         * page   当前页
         * records 总条数
         *
         **/
        Integer start = (page - 1) * rows;
        List<Chapter> chapters = chapterMapper.chapterFindByPage(start, rows, albumId);

        Integer count = chapterMapper.count(albumId);
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;

        map.put("rows", chapters);
        map.put("records", count);
        map.put("total", total);
        map.put("page", page);
        return map;
    }

    @Override
    public String save(Chapter chapter, String albumId) {
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapter.setAlbumId(albumId);
        chapter.setSize("1");
        chapter.setTimeLong("1");
        chapter.setCreateDate(new Date());

        Integer count = chapterMapper.seleteAlbumCount(albumId);
        chapterMapper.updateAlbumCount(count + 1, albumId);

        chapterMapper.save(chapter);
        return s;
    }

    @Override
    public void updatePath(String chapterId, String newPath) {
        chapterMapper.updatePath(chapterId, newPath);
    }

    @Override
    public void updateSzieAndLong(String ll, String bg, String chapterId) {
        chapterMapper.updateSzieAndLong(ll, bg, chapterId);
    }

    @Override
    public void deleteChapter(String[] ids, String albumId) {
        for (String id : ids) {
            chapterMapper.deleteChapter(id);
        }
        Integer count = chapterMapper.seleteAlbumCount(albumId);
        chapterMapper.updateAlbumCount(count - ids.length, albumId);
    }

    @Override
    public void update(String title, String chapterId) {
        chapterMapper.update(title, chapterId);
    }
}
