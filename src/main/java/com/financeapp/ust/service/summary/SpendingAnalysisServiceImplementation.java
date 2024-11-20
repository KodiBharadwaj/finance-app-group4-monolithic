package com.financeapp.ust.service.summary;

import com.financeapp.ust.dto.featuresDto.SpendingPatternDto;
import com.financeapp.ust.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpendingAnalysisServiceImplementation implements SpendingAnalysisService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<SpendingPatternDto> analyzeSpendingPattern(int userId) {

        LocalDate currentStart = LocalDate.now().withDayOfMonth(1); // Start of this month
        LocalDate currentEnd = currentStart.plusMonths(1).minusDays(1); // End of this month
        LocalDate previousStart = currentStart.minusMonths(1); // Start of last month
        LocalDate previousEnd = currentStart.minusDays(1); // End of last month

        // Fetch categorized expenses for both periods
        List<ExpenseRepository.CategoryExpense> currentExpenses = expenseRepository.findTotalExpensesByCategoryAndPeriod(userId, currentStart, currentEnd);
        List<ExpenseRepository.CategoryExpense> previousExpenses = expenseRepository.findTotalExpensesByCategoryAndPeriod(userId, previousStart, previousEnd);

        // Convert previous expenses list to a map for easier lookup
        Map<String, Double> previousExpensesMap = previousExpenses.stream()
                .collect(Collectors.toMap(ExpenseRepository.CategoryExpense::getCategory, ExpenseRepository.CategoryExpense::getTotalSpent));

        List<SpendingPatternDto> insights = new ArrayList<>();

        for (ExpenseRepository.CategoryExpense currentExpense : currentExpenses) {
            String category = currentExpense.getCategory();
            double currentTotal = currentExpense.getTotalSpent();
            double previousTotal = previousExpensesMap.getOrDefault(category, 0.0);

            // Calculate the percentage change in spending
            double percentageChange = previousTotal == 0 ? 100 : ((currentTotal - previousTotal) / previousTotal) * 100;

            // Generate insight message based on the change
            String insightMessage;
            if (percentageChange > 20) {
                insightMessage = "Your " + category + " expenses are " + String.format("%.2f", percentageChange) + "% higher this month compared to last month.";
            } else if (percentageChange < -20) {
                insightMessage = "Good job! Your " + category + " expenses are " + String.format("%.2f", Math.abs(percentageChange)) + "% lower this month compared to last month.";
            } else {
                insightMessage = "Your " + category + " spending is consistent with last month.";
            }

            insights.add(new SpendingPatternDto(category, currentTotal, previousTotal, insightMessage));
        }
        return insights;
    }
}
