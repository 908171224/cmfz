package com.baizhi.mapper;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterMapper {
    public Integer count(String albumId);

    public List<Chapter> chapterFindByPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("albumId") String albumId);

    public void save(Chapter chapter);

    public void updatePath(@Param("id") String id, @Param("newPath") String newPath);

    public void updateSzieAndLong(@Param("ll") String ll, @Param("bg") String bg, @Param("id") String id);

    public Integer seleteAlbumCount(String albumId);

    public void updateAlbumCount(@Param("count") Integer count, @Param("albumId") String albumId);

    public void deleteChapter(String id);

    public void update(@Param("title") String title, @Param("chapterId") String chapterId);
}
