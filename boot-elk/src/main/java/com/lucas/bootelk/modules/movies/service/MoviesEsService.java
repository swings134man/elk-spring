package com.lucas.bootelk.modules.movies.service;

import com.lucas.bootelk.modules.movies.domain.MoviesDocument;
import com.lucas.bootelk.modules.movies.repository.MoviesEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoviesEsService {

    private final MoviesEsRepository moviesEsRepository;

    public List<MoviesDocument> findByTitle(String title) {
        SearchHits<MoviesDocument> result = moviesEsRepository.findByTitle(title);
        log.info("result: {}", result);

        List<MoviesDocument> resultList = new ArrayList<>();

        result.forEach(moviesDocumentSearchHit -> {
            resultList.add(moviesDocumentSearchHit.getContent());
        });

        return resultList;
    }
}
