package com.financeapp.ust.service;

import com.financeapp.ust.dto.IncomeDto;
import com.financeapp.ust.dto.summaryDto.IncomeSummaryDto;

import java.util.List;

public interface IncomeService {

    IncomeDto save(int id, IncomeDto incomeDto);

    List<IncomeSummaryDto> getAllIncomes(int userId);

    IncomeDto updateById(int userId, String source, IncomeDto incomeDto);

    void deleteByIdAndSource(int userId, String source);

}
