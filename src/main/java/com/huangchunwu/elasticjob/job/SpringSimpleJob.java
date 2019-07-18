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
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.google.gson.Gson;
import com.huangchunwu.elasticjob.domain.Article;
import com.huangchunwu.elasticjob.repository.IArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
public class SpringSimpleJob implements SimpleJob {
    
    @Resource
    private IArticleRepository articleRepository;
    
    @Override
    public void execute(final ShardingContext shardingContext) {
        List<Article> data = articleRepository.findTodoData(shardingContext.getShardingParameter(), 10);
        if (CollectionUtils.isEmpty(data)){
            log.warn("SpringSimpleJob ：query some data from db  is  empty，param:{}", new Gson().toJson(shardingContext));
            return;
        }else {
            log.info("SpringSimpleJob：query some data from db  is  empty，param:{}", new Gson().toJson(shardingContext));
        }
        for (Article each : data) {
            articleRepository.setCompleted(each.getId());
        }
    }
}
