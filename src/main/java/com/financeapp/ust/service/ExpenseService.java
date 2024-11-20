package com.financeapp.ust.service;

import com.financeapp.ust.dto.ExpenseDto;
import com.financeapp.ust.dto.featuresDto.BudgetAlertDto;
import com.financeapp.ust.dto.summaryDto.ExpenseSummaryDto;

import java.util.List;

public interface ExpenseService {

    ExpenseDto save(int id, ExpenseDto expenseDto);

    List<ExpenseSummaryDto> getAllExpenses(int userId);

    ExpenseDto updateByIdAndCategory(int userId, String category, ExpenseDto expenseDto);

    void delete(int userId, String category);

}
