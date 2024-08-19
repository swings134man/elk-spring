package com.lucas.bootelk.rest;

import com.lucas.bootelk.modules.movies.domain.MoviesDocument;
import com.lucas.bootelk.modules.movies.service.MoviesEsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/movies")
@RequiredArgsConstructor
@Slf4j
public class MoviesEsController {

    private final MoviesEsService moviesEsService;

    @PostMapping("/save")
    public ResponseEntity<MoviesDocument> saveMovie(@RequestBody MoviesDocument moviesDocument) {
        MoviesDocument res = moviesEsService.saveMovie(moviesDocument);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<List<MoviesDocument>> getMoviesByQuery(@RequestParam MultiValueMap<String, Object> params) {
        List<MoviesDocument> result = moviesEsService.findByQuery(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/title")
    public List<MoviesDocument> getMoviesByTitle(String title) {
        return moviesEsService.findByTitle(title);
    }


    @GetMapping("/all")
    public List<MoviesDocument> getAllMovies() {
        return moviesEsService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMovieById(@PathVariable String id) {
        moviesEsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
