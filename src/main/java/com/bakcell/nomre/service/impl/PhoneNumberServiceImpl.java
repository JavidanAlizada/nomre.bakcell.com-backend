package com.bakcell.nomre.service.impl;

import com.bakcell.nomre.factory.PhoneNumberResponseFactory;
import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.model.request.PhoneNumberRequest;
import com.bakcell.nomre.model.response.PhoneNumberResponse;
import com.bakcell.nomre.repository.PhoneNumberCounterRepository;
import com.bakcell.nomre.repository.PhoneNumberRepository;
import com.bakcell.nomre.service.PhoneNumbersService;
import com.bakcell.nomre.util.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Javidan Alizada
 */
@Service
@RequiredArgsConstructor
public class PhoneNumberServiceImpl implements PhoneNumbersService {

    private final PhoneNumberRepository repository;
    private final PhoneNumberCounterRepository counterRepository;
    private final PhoneNumberResponseFactory factory;

    @Override
    @SneakyThrows
    public PhoneNumberResponse findByQuery(PhoneNumberRequest request) {
        Validator.validate(request.getPrefix());
        return this.factory.msisdn(request.getMsisdn())
                .categoryName(Objects.nonNull(request.getCategory()) ? request.getCategory().toString() : null)
                .prefix(request.getPrefix())
                .getPhoneNumberResponse();
    }

    @Override
    public void update(PhoneNumberEntity entity) {
        this.repository.save(entity);
    }

    @Override
    public PhoneNumberResponse findAll() {
        List<PhoneNumberEntity> phoneNumberEntities = new ArrayList<>();
        this.repository.findAll().forEach(phoneNumberEntities::add);
        Long totalCountOfMSISDN = counterRepository.countByReservedFalse();
        return PhoneNumberResponse.builder().msisdnList(phoneNumberEntities).total(totalCountOfMSISDN).build();
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @Override
    public List<PhoneNumberEntity> findAllByMsisdn(List<String> msisdnList) {
        return this.repository.findAllByMsisdn(msisdnList);
    }

}
