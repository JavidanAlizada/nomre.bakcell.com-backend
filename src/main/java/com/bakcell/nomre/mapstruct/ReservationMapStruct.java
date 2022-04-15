package com.bakcell.nomre.mapstruct;

import com.bakcell.nomre.model.entity.ReservationEntity;
import com.bakcell.nomre.model.request.ReserveRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Javidan Alizada
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ReservationMapStruct {

    @Mapping(target = "subscriberNumber", source = "subscriberNumber")
    @Mapping(target = "msisdn", source = "msisdn")
    public abstract ReservationEntity map(ReserveRequest reserveRequest);
}
