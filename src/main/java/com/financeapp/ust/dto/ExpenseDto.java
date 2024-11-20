package com.financeapp.ust.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeapp.ust.model.User;

import java.time.LocalDate;

public record ExpenseDto(int id, String category, double amount, LocalDate date, User user) {
}
