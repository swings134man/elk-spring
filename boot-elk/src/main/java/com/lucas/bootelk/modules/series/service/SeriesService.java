package com.lucas.bootelk.modules.series.service;

import com.lucas.bootelk.modules.series.domain.SeriesDocument;
import com.lucas.bootelk.modules.series.repository.SeriesEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeriesService {

    private final SeriesEsRepository repository;

    @Transactional(readOnly = true)
    public List<SeriesDocument> findAllSeries() {
        Iterable<SeriesDocument> all = repository.findAll();

        List<SeriesDocument> list = new ArrayList<>();
        all.forEach(list::add);
        return list;
    }
}
