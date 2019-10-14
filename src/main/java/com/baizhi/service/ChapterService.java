package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.Map;

public interface ChapterService {
    public Map<String, Object> chapterFindByPage(Integer page, Integer rows, String albumId);

    public String save(Chapter chapter, String albumId);

    public void updatePath(String chapterId, String newPath);

    public void updateSzieAndLong(String ll, String bg, String chapterId);

    public void deleteChapter(String[] ids, String albumId);

    public void update(String title, String chapterId);
}
