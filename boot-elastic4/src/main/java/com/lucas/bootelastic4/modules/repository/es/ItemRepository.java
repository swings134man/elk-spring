package com.lucas.bootelastic4.modules.repository.es;

import com.lucas.bootelastic4.modules.item.domain.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {
}
