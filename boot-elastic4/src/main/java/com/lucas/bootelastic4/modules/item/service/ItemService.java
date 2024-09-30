package com.lucas.bootelastic4.modules.item.service;

import com.lucas.bootelastic4.common.utils.EsCsvParser;
import com.lucas.bootelastic4.modules.item.domain.Item;
import com.lucas.bootelastic4.modules.item.repository.ItemQueryRepository;
import com.lucas.bootelastic4.modules.repository.es.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final EsCsvParser csvParser;
    private final ItemRepository itemRepository;
    private final ItemQueryRepository itemQueryRepository;


    @Transactional
    public List<Item> saveAllByCsv(String csvFileName) throws InstantiationException, IllegalAccessException {
        Item item = new Item();

        List<Object> objects = csvParser.parseToList(csvFileName, item);

        // Parse To Object To Entity
        List<Item> items =
                objects.stream()
                        .filter(Item.class::isInstance)
                        .map(Item.class::cast)
                        .collect(Collectors.toList());

        log.info("--- items CSV Save Start ---");
        itemRepository.saveAll(items);
        log.info("--- items CSV Save End ---");

        return items;
    }

    @Transactional(readOnly = true)
    public List<Item> findByNameFuzzyAuto(String name) {
        return itemQueryRepository.findByNameWithFuzzy(name);
    }
}
