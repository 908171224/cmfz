package com.baizhi.serviceImpl;

import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> articleFindByPage(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        /** total  总页数
         * page   当前页
         * records 总条数
         *
         **/
        int start = (page - 1) * rows;
        List<Article> articles = articleMapper.articleFindByPage(start, rows);

        Integer count = articleMapper.count();
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;

        map.put("rows", articles);
        map.put("total", total);
        map.put("page", page);
        map.put("records", count);
        return map;
    }

    @Override
    public void add(Article article) {
        String s = UUID.randomUUID().toString();
        article.setId(s);
        article.setCreateDate(new Date());
        articleMapper.add(article);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }
}
