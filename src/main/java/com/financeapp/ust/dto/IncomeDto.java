package com.financeapp.ust.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeapp.ust.model.User;

import java.time.LocalDate;

public record IncomeDto(int id, double amount, String source, LocalDate date, User user) {
}
