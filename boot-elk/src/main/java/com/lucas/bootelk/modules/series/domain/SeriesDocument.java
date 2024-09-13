package com.lucas.bootelk.modules.series.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.join.JoinField;

import java.util.List;

@Document(indexName = "series")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Setting(replicas = 0)
public class SeriesDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String year;

    @Field(type = FieldType.Object)
    private List<String> genre;


    @JoinTypeRelations(relations = {
            @JoinTypeRelation(parent = "franchise", children = "film")
    })
    @Field(type = FieldType.Nested, name = "film_to_franchise")
    private JoinField<String> filmToFranchise;
}