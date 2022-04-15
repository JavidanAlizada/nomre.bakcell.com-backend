package com.bakcell.nomre.model.response;

import com.bakcell.nomre.model.entity.ReservationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Javidan Alizada
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllReservations {
    private List<ReservationEntity> reservationEntities;
}
