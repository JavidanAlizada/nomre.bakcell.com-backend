package com.bakcell.nomre.util;

import com.bakcell.nomre.exception.BadPrefixException;

import java.util.List;
import java.util.Objects;

/**
 * @author Javidan Alizada
 */
public class Validator {

    private static final List<String> POSSIBLE_PREFIXES = List.of("099", "055");

    public static void validate(String prefix) {
        if (Objects.nonNull(prefix)) {
            validatePrefix(prefix);
        }
    }

    private static void validatePrefix(String prefix) {
        if (!POSSIBLE_PREFIXES.contains(prefix)) {
            throw new BadPrefixException();
        }
    }


}
