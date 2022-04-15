package com.bakcell.nomre.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

/**
 * @author Javidan Alizada
 */
@Document(indexName = "msisdn")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumberEntity {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Id
    @JsonIgnore
    private String id;

    @Field(name = "prefix", type = FieldType.Text)
    private String prefix;

    @Field(name = "msisdn", type = FieldType.Text)
    private String msisdn;

    @Field(name = "categoryName", type = FieldType.Text)
    private String categoryName;

    @Field(name = "reserved", type = FieldType.Boolean)
    private Boolean reserved;

    public PhoneNumberEntity(String prefix, String msisdn, String categoryName, Boolean reserved) {
        this.prefix = prefix;
        this.msisdn = msisdn;
        this.categoryName = categoryName;
        this.reserved = reserved;
    }

    public static Map<String, Object> getAsMap(final PhoneNumberEntity entity) {
        return OBJECT_MAPPER.convertValue(entity, new TypeReference<>() {
        });
    }
}
