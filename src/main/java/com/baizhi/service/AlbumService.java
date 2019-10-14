package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    public Map<String, Object> albumFindByPage(Integer page, Integer rows);

    public String save(Album album);

    public void updatePath(String id, String newPath);

    public void update(Album album);

    public void delete(String[] id);
}
