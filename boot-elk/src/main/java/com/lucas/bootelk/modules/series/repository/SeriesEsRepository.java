package com.lucas.bootelk.modules.series.repository;

import com.lucas.bootelk.modules.series.domain.SeriesDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SeriesEsRepository extends ElasticsearchRepository<SeriesDocument, String> {
}
