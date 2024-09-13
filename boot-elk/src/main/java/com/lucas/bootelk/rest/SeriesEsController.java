package com.lucas.bootelk.rest;

import com.lucas.bootelk.modules.series.domain.SeriesDocument;
import com.lucas.bootelk.modules.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/series")
public class SeriesEsController {

    private final SeriesService service;


    @GetMapping("/")
    public ResponseEntity<List<SeriesDocument>> findAllSeries() {
        List<SeriesDocument> allSeries = service.findAllSeries();
        return new ResponseEntity<>(allSeries, HttpStatus.OK);
    }
}
