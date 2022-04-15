package com.bakcell.nomre.job;

import com.bakcell.nomre.model.entity.PhoneNumberEntity;
import com.bakcell.nomre.model.entity.ReservationEntity;
import com.bakcell.nomre.service.PhoneNumbersService;
import com.bakcell.nomre.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Javidan Alizada
 */
@Service
@RequiredArgsConstructor
public class ControlJob {

    private static final Integer AVAILABLE_RESERVATION_MINUTE = 5 * 60;
    private static final Integer SECONDS_ON_MINUTE = 60;
    private final PhoneNumbersService phoneNumbersService;
    private final ReservationService reservationService;

    @Scheduled(fixedRate = 60 * 1000)
    public void controlReservation() {
        this.getPhoneNumberEntities(getReservationEntities())
                .forEach(entity -> {
                    ReservationEntity reservationEntity = this.reservationService.findByMsisdn(entity.getMsisdn());
                    checkReservedHoursOfPhoneEntity(reservationEntity, entity);
                });
    }

    private void checkReservedHoursOfPhoneEntity(ReservationEntity reservationEntity, PhoneNumberEntity phoneNumberEntity) {
        if (isAbove5HoursReservationHour(reservationEntity.getReservedAt())) {
            this.updatePhoneNumberReservedStatus(phoneNumberEntity);
            this.deleteReservation(reservationEntity);
        }
    }

    private Long getMinuteDifferenceBetweenReservationAndCurrentDate(LocalDateTime reservedAt) {
        return Duration.between(reservedAt, LocalDateTime.now()).getSeconds() / SECONDS_ON_MINUTE;
    }

    private boolean isAbove5HoursReservationHour(LocalDateTime reservedAt) {
        return getMinuteDifferenceBetweenReservationAndCurrentDate(reservedAt) > AVAILABLE_RESERVATION_MINUTE;
    }

    private List<ReservationEntity> getReservationEntities() {
        return this.reservationService.findAll();
    }

    private List<PhoneNumberEntity> getPhoneNumberEntities(List<ReservationEntity> reservationEntities) {
        return this.phoneNumbersService
                .findAllByMsisdn(reservationEntities.stream()
                        .map(ReservationEntity::getId)
                        .collect(toList()));
    }

    private void updatePhoneNumberReservedStatus(PhoneNumberEntity entity) {
        entity.setReserved(Boolean.FALSE);
        entity.setId(entity.getId());
        this.phoneNumbersService.update(entity);
    }

    private void deleteReservation(ReservationEntity entity) {
        entity.setActive(Boolean.FALSE);
        entity.setId(entity.getId());
        this.reservationService.update(entity);
    }
}
