package com.baizhi.ArticleRepsitory;

import com.baizhi.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepsitory extends ElasticsearchRepository<Article, String> {
}
