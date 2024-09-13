package com.lucas.bootelastic4.modules.repository.es;

import com.lucas.bootelastic4.modules.movies.domain.MovieDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

// This Class Is Used For ElasticSearch Repository
@Repository
public interface MovieSearchRepository extends ElasticsearchRepository<MovieDocument, String> {

}
