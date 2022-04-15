package com.bakcell.nomre.repository;

import com.bakcell.nomre.model.entity.ReservationEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Javidan Alizada
 */
@Repository
public interface ReservationRepository extends ElasticsearchRepository<ReservationEntity, String> {

    List<ReservationEntity> findByReservedAtBetweenAndSubscriberNumberIs(LocalDateTime date,LocalDateTime now, String subscriberNumber);

    ReservationEntity findByMsisdn(String msisdn);
}
