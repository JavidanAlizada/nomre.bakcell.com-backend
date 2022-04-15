package com.bakcell.nomre.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Javidan Alizada
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveRequest {

    @JsonProperty("subscriberNumber")
    private String subscriberNumber;

    @JsonProperty("msisdn")
    private String msisdn;
}
