package com.bakcell.nomre.service.impl;

import com.bakcell.nomre.enums.Status;
import com.bakcell.nomre.mapstruct.ReservationMapStruct;
import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.model.entity.ReservationEntity;
import com.bakcell.nomre.model.request.PhoneNumberRequest;
import com.bakcell.nomre.model.request.ReserveRequest;
import com.bakcell.nomre.model.response.PhoneNumberResponse;
import com.bakcell.nomre.model.response.ReservationResponse;
import com.bakcell.nomre.repository.ReservationRepository;
import com.bakcell.nomre.service.PhoneNumbersService;
import com.bakcell.nomre.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Javidan Alizada
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private static final Integer MAX_RESERVATION_COUNT_PER_DAY = 5;
    private final ReservationRepository repository;
    private final PhoneNumbersService numbersService;
    private final ReservationMapStruct mapStruct;

    @Override
    @Transactional
    public ReservationResponse reserve(ReserveRequest reserveRequest) {
        ReservationEntity entity = this.mapStruct.map(reserveRequest);
        entity.setReservedAt(LocalDateTime.now());
        entity.setActive(Boolean.TRUE);
        try {
            PhoneNumberEntity phoneNumberEntity = this.getPhoneNumberEntityByMsisdn(entity.getMsisdn());
            if (this.isNotReserved(phoneNumberEntity)
                    && this.hasPermissionForReserving(entity.getSubscriberNumber())) {
                this.repository.save(entity);
                this.updateNumberReservedStatusByMSISDN(phoneNumberEntity);
                return ReservationResponse.builder().status(Status.SUCCESS).build();
            }
            return ReservationResponse.builder().status(Status.FAIL).build();
        } catch (Exception exception) {
            return ReservationResponse.builder().status(Status.FAIL).build();
        }
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

    @Override
    public List<ReservationEntity> findAll() {
        return Streamable.of(this.repository.findAll()).toList();
    }

    @Override
    public ReservationEntity findByMsisdn(String msisdn) {
        return this.repository.findByMsisdn(msisdn);
    }

    @Override
    public void update(ReservationEntity reservationEntity) {
        this.repository.save(reservationEntity);
    }

    private boolean hasPermissionForReserving(String subscriberNumber) {
        LocalDateTime now = LocalDateTime.now();
        List<ReservationEntity> entities = this.repository.findByReservedAtBetweenAndSubscriberNumberIs(now.minusHours(24), now, subscriberNumber);
        return entities.size() < MAX_RESERVATION_COUNT_PER_DAY;
    }

    private PhoneNumberEntity getPhoneNumberEntityByMsisdn(String msisdn) {
        PhoneNumberResponse response = this.numbersService.findByQuery(PhoneNumberRequest.builder().msisdn(msisdn).build());
        return response.getMsisdnList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Exception occured at get phoneNumber by msisdn"));
    }

    private boolean isNotReserved(PhoneNumberEntity entity) {
        return !entity.getReserved();
    }

    private void updateNumberReservedStatusByMSISDN(PhoneNumberEntity entity) {
        entity.setReserved(Boolean.TRUE);
        entity.setId(entity.getId());
        this.numbersService.update(entity);

    }

}
