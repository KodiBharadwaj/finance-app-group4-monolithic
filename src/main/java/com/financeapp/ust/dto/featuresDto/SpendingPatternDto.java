package com.financeapp.ust.dto.featuresDto;

public record SpendingPatternDto(String category, double currentPeriodTotal, double previousPeriodTotal,
                                 String insight) {
}

