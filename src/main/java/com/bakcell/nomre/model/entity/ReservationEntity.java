package com.bakcell.nomre.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * @author Javidan Alizada
 */
@Document(indexName = "reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

    @Id
    @JsonIgnore
    private String id;

    @Field(name = "subscriberNumber", type = FieldType.Text)
    private String subscriberNumber;

    @Field(name = "msisdn", type = FieldType.Text)
    private String msisdn;

    @Field(name = "reservedAt", type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime reservedAt;

    @Field(name = "active", type = FieldType.Boolean)
    private Boolean active;
}
