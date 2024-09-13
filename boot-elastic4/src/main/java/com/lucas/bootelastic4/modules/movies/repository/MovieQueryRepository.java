package com.lucas.bootelastic4.modules.movies.repository;

import com.lucas.bootelastic4.modules.movies.domain.MovieDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Movie Query Repository
 * - This Class Is Used For Querying Data From ElasticSearch
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class MovieQueryRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    // FIXME: TO Dynamic Query Changed
    public List<MovieDocument> findByQuery(MovieDocument document) {
        String title = document.getTitle();

        Query query = NativeQuery.builder()
                .withQuery(q -> q.match(m ->
                        m.field("title").query(title)))
                .build();

        return elasticsearchOperations.search(query, MovieDocument.class)
                .stream().map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }
}
