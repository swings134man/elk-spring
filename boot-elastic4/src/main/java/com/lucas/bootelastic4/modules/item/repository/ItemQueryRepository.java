package com.lucas.bootelastic4.modules.item.repository;

import com.lucas.bootelastic4.modules.item.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemQueryRepository {

    private final ElasticsearchOperations elasticsearchOperations;


    /**
     * Find By Name - with Fuzzy Search
     * - This Index's "name" field is search-as-you-type enabled
     * @param name
     * @return
     */
    public List<Item> findByNameWithFuzzy(String name) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.match(f -> f.field("name")
                        .query(name).fuzziness("2")))
                .build();

        return elasticsearchOperations.search(query, Item.class)
                .stream().map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

    /**
     * Find By Name -> No fuzzy search
     * -> And Analyzer is "standard"
     * @param name
     * @return
     */
    public List<Item> findByName(String name) {
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.match(f -> f.field("name")
                        .query(name)
                        .analyzer("standard")))
                .build();

        return elasticsearchOperations.search(query, Item.class)
                .stream().map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

}
