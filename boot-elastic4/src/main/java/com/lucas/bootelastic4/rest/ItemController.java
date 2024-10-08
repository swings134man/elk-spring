package com.lucas.bootelastic4.rest;

import com.lucas.bootelastic4.common.utils.EsCsvParser;
import com.lucas.bootelastic4.modules.item.domain.Item;
import com.lucas.bootelastic4.modules.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class ItemController {

    private final EsCsvParser csvParser;
    private final ItemService itemService;

    @PostMapping("/items/csv")
    public ResponseEntity<List<Item>> saveCsv(@RequestParam String csvFileName) throws InstantiationException, IllegalAccessException {
        if(csvFileName.isEmpty() || csvFileName.isBlank()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Item> items = itemService.saveAllByCsv(csvFileName);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/items/{name}")
    public ResponseEntity<List<Item>> findByNameFuzzyAuto(@PathVariable String name) {
        List<Item> result = itemService.findByNameFuzzyAuto(name);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
