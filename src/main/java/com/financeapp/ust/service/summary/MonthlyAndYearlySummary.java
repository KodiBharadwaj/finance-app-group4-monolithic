package com.financeapp.ust.service.summary;

import com.financeapp.ust.dto.featuresDto.MonthlyAndYearlySummaryDto;

public interface MonthlyAndYearlySummary {
    MonthlyAndYearlySummaryDto getMonthlySummary(int id, int month, int year);

    MonthlyAndYearlySummaryDto getYearlySummary(int id, int year);
}
