package com.lending.lendingbackend.data.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CreditStatus {
    ACTIVE,
    EXPIRED,
    CLOSED,
    AWAITING_CONFIRMATION;
    private static final Map<String, String> STATUS_TITLES = new LinkedHashMap<>();


    static {
        STATUS_TITLES.put("ACTIVE", "Активный");
        STATUS_TITLES.put("EXPIRED", "Просрочен");
        STATUS_TITLES.put("CLOSED", "Закрыт");
        STATUS_TITLES.put("AWAITING_CONFIRMATION","Ожидает подтверждения");
    }

    public static Map<String, String> getStatusTitles() {
        return STATUS_TITLES;
    }
}
