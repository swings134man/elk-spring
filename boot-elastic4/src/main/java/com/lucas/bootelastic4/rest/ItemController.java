package com.lucas.bootelastic4.rest;

import com.lucas.bootelastic4.common.utils.EsCsvParser;
import com.lucas.bootelastic4.modules.item.domain.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ItemController {

    private final EsCsvParser csvParser;

    @PostMapping("/items/csv")
    public List<Item> saveCsv(@RequestParam String csvFileName) throws InstantiationException, IllegalAccessException {
        Item item = new Item();

        List<Object> objects = csvParser.parseToList(csvFileName, item);

        // TODO: Send To ES Data Save
        // Parse To Object To Entity
        List<Item> items =
                objects.stream()
                        .filter(Item.class::isInstance)
                        .map(Item.class::cast)
                        .collect(Collectors.toList());

        log.info("items = " + items);
        return items;
    }
}
