package com.bakcell.nomre.repository;

import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Javidan Alizada
 */
@Repository
public interface PhoneNumberCounterRepository extends ElasticsearchRepository<PhoneNumberEntity, String> {

    Long countByPrefixLikeAndReservedFalse(String prefix);

    Long countByCategoryNameLikeAndReservedFalse(String categoryName);

    Long countByReservedFalse();
}
