package com.lending.lendingbackend.data.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public enum PaymentType {
    ANNUITY,
    DIFFERENTIATED;

    private static final Map<String, String> TYPE_TITLES = new LinkedHashMap<>();

    static {
        TYPE_TITLES.put("ANNUITY", "Аннуитетный");
        TYPE_TITLES.put("DIFFERENTIATED", "Дифференцированный");
    }

    public static Map<String, String> getTypeTitles() {
        return TYPE_TITLES;
    }
}
