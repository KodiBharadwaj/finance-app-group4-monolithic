package com.financeapp.ust.dto.featuresDto;

public record BudgetAlertDto(String category, double currentSpending, double budgetLimit, String alertMessage) {
}
