package com.bakcell.nomre.dao;

import com.bakcell.nomre.model.response.PhoneNumberResponse;

/**
 * @author Javidan Alizada
 */
public interface PhoneNumberDao {

    PhoneNumberResponse findByMsisdn(String msisdn);

    PhoneNumberResponse findByPrefix(String prefix);

    PhoneNumberResponse findByCategoryName(String categoryName);

    PhoneNumberResponse findByPrefixAndMsisdn(String prefix, String msisdn);

    PhoneNumberResponse findByPrefixAndCategoryName(String prefix, String categoryName);

    PhoneNumberResponse findByPrefixAndMsisdnAndCategoryName(String prefix, String msisdn, String categoryName);

    PhoneNumberResponse findByMsisdnAndCategoryName(String msisdn, String categoryName);

}
