package com.financeapp.ust.dto.summaryDto;

import java.time.LocalDate;

public record IncomeSummaryDto(int id, double amount, String source, LocalDate date) {
}
