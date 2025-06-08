package com.lending.lendingbackend.data.entity;

import lombok.Data;


public enum TransactionType {
    DOWN_PAYMENT("Первоначальный платеж"),
    MONTHLY_PAYMENT("Ежемесячный платеж"),
    COMMISSION("Начисление процентов"),
    PENALTIES("Начисление штрафа"),
    EARLY_REPAYMENT("Досрочное погашение");


    private String russianName;
    public String getRussianName() {
        return russianName;
    }


    TransactionType(String russianName) {
        this.russianName =  russianName;
    }
}
