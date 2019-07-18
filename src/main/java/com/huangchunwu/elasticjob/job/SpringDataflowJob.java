/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.huangchunwu.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.google.gson.Gson;
import com.huangchunwu.elasticjob.domain.Article;
import com.huangchunwu.elasticjob.repository.IArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

@Slf4j
public class SpringDataflowJob implements DataflowJob<Article> {
    
    @Resource
    private IArticleRepository articleRepository;
    
    @Override
    public List<Article> fetchData(final ShardingContext shardingContext) {
     List<Article> data = articleRepository.findTodoData(shardingContext.getShardingParameter(), 10);
     if (CollectionUtils.isEmpty(data)){
         log.warn("fetchData: query some data from db  is  empty，param:{}", new Gson().toJson(shardingContext));
     }
     return data;
    }
    
    @Override
    public void processData(final ShardingContext shardingContext, final List<Article> data) {
        if (CollectionUtils.isEmpty(data)){
            log.warn("processData : query some data from db  is  empty，param:{}", new Gson().toJson(shardingContext));
            return;
        }
        for (Article each : data) {
            articleRepository.setCompleted(each.getId());
        }
    }
}
