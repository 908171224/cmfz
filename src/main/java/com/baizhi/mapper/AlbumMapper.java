package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumMapper {
    public Integer count();

    public List<Album> albumFindByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    public void save(Album album);

    public void updatePath(@Param("id") String id, @Param("newPath") String newPath);

    public void update(Album album);

    public void delete(String id);

    public void deleteAllChapter(String id);
}
