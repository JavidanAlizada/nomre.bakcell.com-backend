package com.bakcell.nomre.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Javidan Alizada
 */
public enum Category {
    SIMPLE_99("SIMPLE_99"),
    NICE_LOW_55("NICE_LOW_55"),
    NICE_HIGH_55("NICE_HIGH_55"),
    SILVER_99("SILVER_99"),
    GENERAL("GENERAL"),
    BRONZE_99("BRONZE_99"),
    PLATINUM_99("PLATINUM_99"),
    GOLD_99("GOLD_99");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Category getCategoryByValue(String value) {
        for (Category category : Category.values()) {
            if (category.getValue().equals(value)) {
                return category;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }

}
