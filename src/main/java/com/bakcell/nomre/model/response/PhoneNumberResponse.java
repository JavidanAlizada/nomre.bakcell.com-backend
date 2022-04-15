package com.bakcell.nomre.model.response;

import com.bakcell.nomre.model.entity.PhoneNumberEntity;
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
public class PhoneNumberResponse {

    private List<PhoneNumberEntity> msisdnList;
    private Long total;
}
