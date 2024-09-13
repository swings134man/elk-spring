package com.lucas.bootelastic4.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * ElasticSearch Config
 */
//@EnableElasticsearchRepositories(basePackages = "com.lucas.bootelastic4.modules.repository.es") //FIXME: IF Use JPA Repository and ElasticSearch Repository together, you should use this annotation
@EnableElasticsearchRepositories
@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("192.168.35.135:9200")
                .build();
    }

}
