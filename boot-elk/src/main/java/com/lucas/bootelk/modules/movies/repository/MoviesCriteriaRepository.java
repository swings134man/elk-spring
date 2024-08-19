package com.lucas.bootelk.modules.movies.repository;

import com.lucas.bootelk.modules.movies.domain.MoviesDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Criteria Repository - Movies Index
 * ES Criteria API를 이용한 동적 쿼리 처리
 * -> BoolQueryBuiler, NativeQuery 도 사용 가능하지만, RestClient(ES) 사용해야 하고, spring-data-es 에서는 CriteriaQuery를 사용해야함.
 * 4.X 이상 버전에서는 RestClient 사용을 강제하고 있기때문에 상기 2개와 spring-data-jpa 를 혼용해서 사용해야 한다.
 */
@Repository
@RequiredArgsConstructor
public class MoviesCriteriaRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    public List<MoviesDocument> findByCriteriaQuery(MultiValueMap<String, Object> params) {
        CriteriaQuery criteriaQuery = queryCondition(params);

        SearchHits<MoviesDocument> result = elasticsearchOperations.search(criteriaQuery, MoviesDocument.class);
        return result.stream()
                .map(searchHit -> searchHit.getContent())
                .collect(Collectors.toList());
    }

    private CriteriaQuery queryCondition(MultiValueMap<String, Object> params) {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria());

        if(params.get("title") != null) {
            criteriaQuery.addCriteria(Criteria.where("title").is(params.get("title")));
        }

        if(params.get("genre") != null){
            if(params.get("genre").size() == 1) { // genre is String
                criteriaQuery.addCriteria(Criteria.where("genre").is(params.get("genre").get(0)));
            }else{
                criteriaQuery.addCriteria(Criteria.where("genre").in(params.get("genre")));
            }
        }

        return criteriaQuery;
    }

}
