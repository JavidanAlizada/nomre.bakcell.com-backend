package com.bakcell.nomre.service;

import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.model.request.PhoneNumberRequest;
import com.bakcell.nomre.model.response.PhoneNumberResponse;

import java.util.List;

/**
 * @author Javidan Alizada
 */
public interface PhoneNumbersService {

    PhoneNumberResponse findByQuery(PhoneNumberRequest phoneNumberRequest);

    void update(PhoneNumberEntity entity);

    PhoneNumberResponse findAll();

    void deleteAll();

    List<PhoneNumberEntity> findAllByMsisdn(List<String> msisdnList);

}
