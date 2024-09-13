package com.lucas.bootelastic4.rest;

import com.lucas.bootelastic4.modules.movies.domain.MovieDocument;
import com.lucas.bootelastic4.modules.movies.service.MovieService;
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
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    @GetMapping("/")
    public ResponseEntity<List<MovieDocument>> findAllMovie() {
        List<MovieDocument> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<List<MovieDocument>> findMovieQuery(MovieDocument document) {
        List<MovieDocument> byQuery = service.findByQuery(document);
        return new ResponseEntity<>(byQuery, HttpStatus.OK);
    }
}
