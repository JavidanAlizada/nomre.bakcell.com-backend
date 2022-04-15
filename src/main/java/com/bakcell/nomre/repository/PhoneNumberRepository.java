package com.bakcell.nomre.repository;

import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Javidan Alizada
 */
@Repository
public interface PhoneNumberRepository extends ElasticsearchRepository<PhoneNumberEntity, String> {

    List<PhoneNumberEntity> findByPrefixLikeAndReservedFalse(String prefix);

    List<PhoneNumberEntity> findByPrefixLikeAndMsisdnLikeAndCategoryNameLikeAndReservedFalse(String prefix, String msisdn, String categoryName);

    List<PhoneNumberEntity> findByCategoryNameLikeAndReservedFalse(String categoryName);

    List<PhoneNumberEntity> findAllByMsisdn(List<String> msisdnList);

}
