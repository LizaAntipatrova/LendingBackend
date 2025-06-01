package com.lending.lendingbackend.data.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ApplicationStatus {
    IN_PROGRESS,
    REJECTED,
    APPROVED;

    private static final Map<String, String> STATUS_TITLES = new LinkedHashMap<>();

    static {
        STATUS_TITLES.put("IN_PROGRESS", "В обработке");
        STATUS_TITLES.put("REJECTED", "Отклонена");
        STATUS_TITLES.put("APPROVED", "Одобрена");
    }

    public static Map<String, String> getTypeTitles() {
        return STATUS_TITLES;
    }
}
