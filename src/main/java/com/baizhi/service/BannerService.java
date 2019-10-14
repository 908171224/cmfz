package com.baizhi.service;

import com.baizhi.entity.Banner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BannerService {
    public Map<String, Object> findByPage(Integer page, Integer rows);

    public String save(Banner banner);

    public void updatePath(String bannerId, String newPath);

    public void update(Banner banner);

    public void deleteById(String[] ids);

    public List<Banner> findAll(HttpServletResponse response) throws IOException;
}
