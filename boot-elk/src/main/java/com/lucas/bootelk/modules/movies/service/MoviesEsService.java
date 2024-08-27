package com.lucas.bootelk.modules.movies.service;

import com.lucas.bootelk.modules.movies.domain.MoviesDocument;
import com.lucas.bootelk.modules.movies.repository.MoviesCriteriaRepository;
import com.lucas.bootelk.modules.movies.repository.MoviesEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoviesEsService {

    private final MoviesEsRepository moviesEsRepository;
    private final MoviesCriteriaRepository moviesCriteriaRepository;

    @Transactional
    public MoviesDocument saveMovie(MoviesDocument moviesDocument) {
        MoviesDocument save = moviesEsRepository.save(moviesDocument);

        if(save.getId() != null) {
            return save;
        }else {
            throw new RuntimeException("Failed to save movie");
        }
    }

    // Dynamic Query
    @Transactional(readOnly = true)
    public List<MoviesDocument> findByQuery(MultiValueMap<String, Object> params) {
        List<MoviesDocument> result = moviesCriteriaRepository.findByCriteriaQuery(params);
        log.info("result: {}", result);

        return result;
    }

    @Transactional(readOnly = true)
    public List<MoviesDocument> findByTitle(String title) {
        SearchHits<MoviesDocument> result = moviesEsRepository.findByTitle(title);
        log.info("result: {}", result);

        List<MoviesDocument> resultList = new ArrayList<>();

        result.forEach(moviesDocumentSearchHit -> {
            resultList.add(moviesDocumentSearchHit.getContent());
        });

        return resultList;
    }

    @Transactional(readOnly = true)
    public SearchHits<MoviesDocument> findByTitleToHit(String title) {
        SearchHits<MoviesDocument> result = moviesEsRepository.findByTitle(title);
        return result;
    }

    @Transactional(readOnly = true)
    public List<MoviesDocument> findAll() {
        Iterable<MoviesDocument> all = moviesEsRepository.findAll();

        List<MoviesDocument> resultList = new ArrayList<>();
        all.forEach(resultList::add);

        return resultList;
    }

    @Transactional
    public void deleteById(String id) {
        try{
            moviesEsRepository.deleteById(id);
        }catch (Exception e){
            log.error("Failed to delete movie with id: {}", id);
            throw new RuntimeException("Failed to delete movie with id: " + id);
        }
    }
}
