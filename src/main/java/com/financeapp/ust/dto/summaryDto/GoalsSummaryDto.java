package com.financeapp.ust.dto.summaryDto;

import java.time.LocalDate;

public record GoalsSummaryDto(int id, String goalName, double currentAmount, double targetAmount, LocalDate deadLine) {
}
