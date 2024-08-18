package com.lucas.bootelk.modules.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "movies")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Setting(replicas = 0)
public class MoviesDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text, docValues = false)
    private String title;

    @Field(type = FieldType.Long)
    private Long year;

    @Field(type = FieldType.Object)
    private List<String> genre = new ArrayList<>();

    @Field(type = FieldType.Text)
    private String actor;
}
