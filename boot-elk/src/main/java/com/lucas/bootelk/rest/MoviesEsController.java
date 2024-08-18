package com.lucas.bootelk.rest;

import com.lucas.bootelk.modules.movies.domain.MoviesDocument;
import com.lucas.bootelk.modules.movies.service.MoviesEsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/movies")
@RequiredArgsConstructor
@Slf4j
public class MoviesEsController {

    private final MoviesEsService moviesEsService;

    @GetMapping("/title")
    public List<MoviesDocument> getMoviesByTitle(String title) {
        return moviesEsService.findByTitle(title);
    }

}
