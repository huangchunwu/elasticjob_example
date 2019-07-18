package com.huangchunwu.elasticjob.repository;


import com.huangchunwu.elasticjob.core.page.PageRequestVo;
import com.huangchunwu.elasticjob.core.page.PageResult;
import com.huangchunwu.elasticjob.domain.Article;
import com.mongodb.client.result.UpdateResult;

import java.util.List;


public interface IArticleRepository{

    Article save(Article article);

    UpdateResult updateArticle(Article article);

    void deleteAll();

    List<Article> findAll();

    void delete(Article article);

    List<Article> findById(String id);

    PageResult<Article> queryPageList(PageRequestVo pageRequest);

    List<Article> findTodoData(String shardingParameter, int i);

    void setCompleted(String id);
}
