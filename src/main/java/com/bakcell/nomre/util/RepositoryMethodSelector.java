package com.bakcell.nomre.util;

import com.bakcell.nomre.dao.PhoneNumberDao;
import com.bakcell.nomre.enums.Category;
import com.bakcell.nomre.model.request.PhoneNumberRequest;
import com.bakcell.nomre.model.response.PhoneNumberResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author Javidan Alizada
 */
@Component
@RequiredArgsConstructor
public class RepositoryMethodSelector {

    private final PhoneNumberDao phoneNumberDao;

    public PhoneNumberResponse byPrefixAndMsisdnAndCategoryName(String prefix, String msisdn, String category) {
        return this.phoneNumberDao.findByPrefixAndMsisdnAndCategoryName(prefix, msisdn, category);
    }

    public PhoneNumberResponse byPrefixAndMsisdn(String prefix, String msisdn) {
        return this.phoneNumberDao.findByPrefixAndMsisdn(prefix, msisdn);
    }

    public PhoneNumberResponse byPrefixAndCategory(String prefix, String category) {
        return this.phoneNumberDao.findByPrefixAndCategoryName(prefix, category);
    }

    public PhoneNumberResponse byPrefix(String prefix) {
        return this.phoneNumberDao.findByPrefix(prefix);
    }

    public PhoneNumberResponse byMsisdnAndCategory(String msisdn, String category) {
        return this.phoneNumberDao.findByMsisdnAndCategoryName(msisdn, category);
    }

    public PhoneNumberResponse byCategory(String category) {
        return this.phoneNumberDao.findByCategoryName(category);
    }

    public PhoneNumberResponse byMsisdn(String msisdn) {
        return this.phoneNumberDao.findByMsisdn(msisdn);
    }

    private String getCategoryValue(Category category) {
        return Optional.ofNullable(category)
                .map(Category::toString)
                .orElse(null);
    }

}
