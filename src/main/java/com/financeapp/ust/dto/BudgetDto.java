package com.financeapp.ust.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeapp.ust.model.User;

public record BudgetDto(int id, String category, double currentSpending, double moneyLimit, User user) {
}
