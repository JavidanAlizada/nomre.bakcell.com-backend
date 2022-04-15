package com.bakcell.nomre.service.impl;

import com.bakcell.nomre.dao.PhoneNumberDao;
import com.bakcell.nomre.enums.Category;
import com.bakcell.nomre.enums.Status;
import com.bakcell.nomre.factory.PhoneNumberResponseFactory;
import com.bakcell.nomre.mapstruct.ReservationMapStruct;
import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.model.entity.ReservationEntity;
import com.bakcell.nomre.model.request.ReserveRequest;
import com.bakcell.nomre.model.response.ReservationResponse;
import com.bakcell.nomre.repository.PhoneNumberCounterRepository;
import com.bakcell.nomre.repository.PhoneNumberRepository;
import com.bakcell.nomre.repository.ReservationRepository;
import com.bakcell.nomre.service.PhoneNumbersService;
import com.bakcell.nomre.util.RepositoryMethodSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Javidan Alizada
 */
@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @InjectMocks
    private ReservationServiceImpl service;

    @Mock
    private ReservationRepository repository;

    @Mock
    private PhoneNumberRepository phoneNumberRepository;

    @Mock
    private ReservationMapStruct mapStruct;

    @Mock
    private PhoneNumbersService numbersService;

    @Mock
    private PhoneNumberCounterRepository counterRepository;

    @Mock
    private PhoneNumberResponseFactory factory;

    @Mock
    private RepositoryMethodSelector repositoryMethodSelector;

    @Mock
    private PhoneNumberDao dao;

    private ReserveRequest request;

    @BeforeEach
    void setUp() {
        request = new ReserveRequest("0513609652", "0991111111");
    }

    @Test
    @Disabled
    void reserve() {
        PhoneNumberEntity phoneNumberEntity = PhoneNumberEntity.builder().id("742893023").categoryName(Category.PLATINUM_99.toString())
                .prefix("099")
                .msisdn("991111111")
                .reserved(Boolean.FALSE)
                .build();

        when(mapStruct.map(request)).thenReturn(
                new ReservationEntity(null, "0513609651", "991111111", null, null));
        when(phoneNumberRepository.save(phoneNumberEntity)).thenReturn(phoneNumberEntity);
        phoneNumberRepository.save(phoneNumberEntity);
        when(mapStruct.map(request)).thenReturn(
                new ReservationEntity(null, "0513609651", "991111111", null, null));
        ReservationEntity entity = mapStruct.map(request);
        when(repository.save(entity)).thenReturn(entity);

        ReservationResponse response = service.reserve(request);

        assertEquals(response.getStatus(), Status.FAIL);
    }
}