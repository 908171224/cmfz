package com.baizhi.mapper;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerMapper {
    public Integer count();

    public List<Banner> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    public void save(Banner banner);

    public void updatePath(@Param("id") String id, @Param("newPath") String newPath);

    public void update(Banner banner);

    public void deleteById(String id);

    public List<Banner> findAll();
}
