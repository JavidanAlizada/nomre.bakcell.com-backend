package com.bakcell.nomre.service;

import com.bakcell.nomre.model.entity.ReservationEntity;
import com.bakcell.nomre.model.request.ReserveRequest;
import com.bakcell.nomre.model.response.ReservationResponse;

import java.util.List;

/**
 * @author Javidan Alizada
 */
public interface ReservationService {

    ReservationResponse reserve(ReserveRequest reserveRequest);

    void deleteAll();

    List<ReservationEntity> findAll();

    ReservationEntity findByMsisdn(String msisdn);

    void update(ReservationEntity reservationEntity);

}
