package com.baizhi.mapper;


import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    public Integer count();

    public List<Article> articleFindByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    public void add(Article article);

    public void update(Article article);

    public List<Article> findAll();
}
