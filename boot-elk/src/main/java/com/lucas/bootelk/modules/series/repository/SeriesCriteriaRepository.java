package com.lucas.bootelk.modules.series.repository;

import com.lucas.bootelk.modules.series.domain.SeriesDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SeriesCriteriaRepository {

    private final ElasticsearchOperations elasticsearchOperations;


    public List<SeriesDocument> findByParentAndChildTitle(String parentType, String title) {
        CriteriaQuery query = new CriteriaQuery(new Criteria());

        query.addCriteria(Criteria.where("film_to_franchise").is(parentType))
                .addCriteria(Criteria.where("title").in(title));

        SearchHits<SeriesDocument> search = elasticsearchOperations.search(query, SeriesDocument.class);
        return search.stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }
}
