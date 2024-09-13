package com.lucas.bootelastic4.modules.movies.service;

import com.lucas.bootelastic4.modules.movies.domain.MovieDocument;
import com.lucas.bootelastic4.modules.movies.repository.MovieQueryRepository;
import com.lucas.bootelastic4.modules.repository.es.MovieSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieSearchRepository movieSearchRepository;
    private final MovieQueryRepository queryRepository;

    @Transactional(readOnly = true)
    public List<MovieDocument> findAll() {
        Iterable<MovieDocument> all = movieSearchRepository.findAll();

        List<MovieDocument> list = new ArrayList<>();
        all.forEach(list::add);

        return list;
    }

    // TEST For Native Query
    @Transactional(readOnly = true)
    public List<MovieDocument> findByQuery(MovieDocument document) {
        List<MovieDocument> byQuery = queryRepository.findByQuery(document);
        log.info("byQuery = {}", byQuery);
        return byQuery;
    }
}
