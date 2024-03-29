package com.huangchunwu.elasticjob;

import com.huangchunwu.elasticjob.domain.Article;
import com.huangchunwu.elasticjob.repository.IArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticjobApplicationTests {
    @Autowired
    private IArticleRepository articleRepository;

    @Test
    public void saveArtical() {
        Article article = new Article();
        article.setTitle("Spring-data-mongo-example");
        article.setAddTime(new Date());
        article.setAuthor("黄春伍");
        article.setTags(Arrays.asList("mongodb","spring"));
        article.setVisitCount(1l);
        articleRepository.save(article);
    }

    @Test
    public void deleteArtical() {
        articleRepository.deleteAll();
    }

}
