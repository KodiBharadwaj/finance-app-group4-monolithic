package com.financeapp.ust.service;

import com.financeapp.ust.dto.BudgetDto;
import com.financeapp.ust.dto.featuresDto.BudgetAlertDto;
import com.financeapp.ust.dto.summaryDto.BudgetSummaryDto;

import java.util.List;

public interface BudgetService {

    BudgetDto save(int id, BudgetDto budgetDto);

    List<BudgetSummaryDto> getAllBudgets(int userId);

    BudgetDto updateByIdAndCategory(int userId, String catergory, BudgetDto budgetDto);

    void delete(int userId, String category);

    List<BudgetAlertDto> getBudgetAlerts(int id);

}
