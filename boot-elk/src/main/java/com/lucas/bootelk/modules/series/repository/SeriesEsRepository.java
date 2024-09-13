package com.lucas.bootelk.modules.series.repository;

import com.lucas.bootelk.modules.series.domain.SeriesDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesEsRepository extends ElasticsearchRepository<SeriesDocument, String> {


}
