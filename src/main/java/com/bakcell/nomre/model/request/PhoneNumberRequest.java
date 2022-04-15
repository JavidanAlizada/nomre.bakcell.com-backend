package com.bakcell.nomre.model.request;

import com.bakcell.nomre.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Javidan Alizada
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumberRequest {
    private String prefix;
    private String msisdn;
    private Category category;
}
