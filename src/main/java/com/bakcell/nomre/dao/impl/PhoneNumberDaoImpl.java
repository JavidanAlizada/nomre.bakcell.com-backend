package com.bakcell.nomre.dao.impl;

import com.bakcell.nomre.dao.PhoneNumberDao;
import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.model.response.PhoneNumberResponse;
import com.bakcell.nomre.repository.PhoneNumberCounterRepository;
import com.bakcell.nomre.repository.PhoneNumberRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Javidan Alizada
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class PhoneNumberDaoImpl implements PhoneNumberDao {

    private final ElasticsearchRestTemplate template;
    private final PhoneNumberRepository repository;
    private final PhoneNumberCounterRepository counterRepository;

    private String getMsisdnSearchRegex(String msisdn) {
        return msisdn.replace("X", ".*");
    }

    private List<PhoneNumberEntity> toList(SearchHits<PhoneNumberEntity> searchHits) {
        List<PhoneNumberEntity> entityList = new ArrayList<>((int) searchHits.getTotalHits());
        searchHits.forEach(searchHit -> entityList.add(searchHit.getContent()));
        return entityList;
    }

    @Override
    @SneakyThrows
    public PhoneNumberResponse findByMsisdn(String msisdn) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withFilter(QueryBuilders.regexpQuery("msisdn", this.getMsisdnSearchRegex(msisdn)))
                .build();
        SearchHits<PhoneNumberEntity> searchHits = this.template.search(nativeSearchQuery, PhoneNumberEntity.class);
        return PhoneNumberResponse.builder()
                .msisdnList(toList(searchHits))
                .total(searchHits.getTotalHits())
                .build();
    }

    @Override
    public PhoneNumberResponse findByPrefix(String prefix) {
        return PhoneNumberResponse.builder()
                .msisdnList(this.repository.findByPrefixLikeAndReservedFalse(prefix))
                .total(this.counterRepository.countByPrefixLikeAndReservedFalse(prefix))
                .build();
    }

    @Override
    public PhoneNumberResponse findByCategoryName(String categoryName) {
        return PhoneNumberResponse.builder()
                .msisdnList(this.repository.findByCategoryNameLikeAndReservedFalse(categoryName))
                .total(this.counterRepository.countByCategoryNameLikeAndReservedFalse(categoryName))
                .build();
    }

    @Override
    public PhoneNumberResponse findByPrefixAndMsisdn(String prefix, String msisdn) {

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.regexpQuery("msisdn", this.getMsisdnSearchRegex(msisdn)))
                .must(QueryBuilders.matchQuery("prefix", prefix));

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<PhoneNumberEntity> searchHits = this.template.search(nativeSearchQuery, PhoneNumberEntity.class);

        return PhoneNumberResponse.builder()
                .msisdnList(toList(searchHits))
                .total(searchHits.getTotalHits())
                .build();
    }

    @Override
    public PhoneNumberResponse findByPrefixAndCategoryName(String prefix, String categoryName) {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("categoryName", categoryName))
                .must(QueryBuilders.matchQuery("prefix", prefix));

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<PhoneNumberEntity> searchHits = this.template.search(nativeSearchQuery, PhoneNumberEntity.class);

        return PhoneNumberResponse.builder()
                .msisdnList(toList(searchHits))
                .total(searchHits.getTotalHits())
                .build();
    }

    @Override
    public PhoneNumberResponse findByPrefixAndMsisdnAndCategoryName(String prefix, String msisdn, String categoryName) {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.regexpQuery("msisdn", this.getMsisdnSearchRegex(msisdn)))
                .must(QueryBuilders.matchQuery("categoryName", categoryName))
                .must(QueryBuilders.matchQuery("prefix", prefix));

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<PhoneNumberEntity> searchHits = this.template.search(nativeSearchQuery, PhoneNumberEntity.class);

        return PhoneNumberResponse.builder()
                .msisdnList(toList(searchHits))
                .total(searchHits.getTotalHits())
                .build();
    }

    @Override
    public PhoneNumberResponse findByMsisdnAndCategoryName(String msisdn, String categoryName) {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.regexpQuery("msisdn", this.getMsisdnSearchRegex(msisdn)))
                .must(QueryBuilders.matchQuery("categoryName", categoryName));

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        SearchHits<PhoneNumberEntity> searchHits = this.template.search(nativeSearchQuery, PhoneNumberEntity.class);

        return PhoneNumberResponse.builder()
                .msisdnList(toList(searchHits))
                .total(searchHits.getTotalHits())
                .build();
    }
}
