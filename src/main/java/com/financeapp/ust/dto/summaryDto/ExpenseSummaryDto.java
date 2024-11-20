package com.financeapp.ust.dto.summaryDto;

import java.time.LocalDate;

public record ExpenseSummaryDto(int id, String category, double amount, LocalDate date) {
}
