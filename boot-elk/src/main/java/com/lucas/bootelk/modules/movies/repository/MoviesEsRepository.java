package com.lucas.bootelk.modules.movies.repository;

import com.lucas.bootelk.modules.movies.domain.MoviesDocument;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MoviesEsRepository extends ElasticsearchRepository<MoviesDocument, String> {
    SearchHits<MoviesDocument> findByTitle(String title);
}
