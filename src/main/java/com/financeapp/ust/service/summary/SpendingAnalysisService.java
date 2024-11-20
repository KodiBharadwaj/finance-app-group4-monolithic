package com.financeapp.ust.service.summary;

import com.financeapp.ust.dto.featuresDto.SpendingPatternDto;

import java.util.List;

public interface SpendingAnalysisService {

    List<SpendingPatternDto> analyzeSpendingPattern(int userId);
}
