package com.bakcell.nomre.service.impl;

import com.bakcell.nomre.enums.Category;
import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.model.request.PhoneNumberRequest;
import com.bakcell.nomre.model.response.PhoneNumberResponse;
import com.bakcell.nomre.repository.PhoneNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Javidan Alizada
 */
@ExtendWith(MockitoExtension.class)
class PhoneNumberServiceImplTest {

    @InjectMocks
    private PhoneNumberServiceImpl service;

    @Mock
    private PhoneNumberRepository repository;

    private PhoneNumberEntity entity;
    private List<PhoneNumberEntity> entities;
    private PhoneNumberRequest request;

    @BeforeEach
    void setUp() {
        entity = new PhoneNumberEntity("001", "099", "0991111111", "GOLD_99", Boolean.FALSE);
        entities = new ArrayList<>(Collections.singletonList(entity));
        request = new PhoneNumberRequest(null, "0991111111", Category.GOLD_99);
    }

    @Test
    @Disabled
    void findByQuery() {
        when(repository.findByPrefixLikeAndMsisdnLikeAndCategoryNameLikeAndReservedFalse(
                request.getPrefix(), request.getMsisdn(), request.getCategory().toString()))
                .thenReturn(entities);

        PhoneNumberResponse response = service.findByQuery(request);

        final int firstElementOfMsISDNList = 0;

        assertEquals(response.getMsisdnList().size(), 1);
        assertEquals(response.getMsisdnList().get(firstElementOfMsISDNList), entity);
    }
}