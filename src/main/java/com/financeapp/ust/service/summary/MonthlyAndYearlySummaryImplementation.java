package com.financeapp.ust.service.summary;

import com.financeapp.ust.dto.featuresDto.MonthlyAndYearlySummaryDto;
import com.financeapp.ust.dto.summaryDto.ExpenseSummaryDto;
import com.financeapp.ust.dto.summaryDto.IncomeSummaryDto;
import com.financeapp.ust.service.ExpenseService;
import com.financeapp.ust.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MonthlyAndYearlySummaryImplementation implements MonthlyAndYearlySummary {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private ExpenseService expenseService;


    @Override
    public MonthlyAndYearlySummaryDto getMonthlySummary(int id, int month, int year) {
        List<IncomeSummaryDto> incomeList = incomeService.getAllIncomes(id);
        double totalMonthlyIncome = incomeList.stream()
                .filter(i -> {
                    LocalDate respectiveDate = i.date();
                    return respectiveDate.getMonthValue() == month && respectiveDate.getYear() == year;
                })
                .mapToDouble(i -> i.amount())
                .sum();

        List<ExpenseSummaryDto> expenseList = expenseService.getAllExpenses(id);
        double totalMonthlyExpenses = expenseList.stream()
                .filter(i -> {
                    LocalDate respectiveDate = i.date();
                    return respectiveDate.getMonthValue() == month && respectiveDate.getYear() == year;
                })
                .mapToDouble(i -> i.amount())
                .sum();

        return new MonthlyAndYearlySummaryDto(totalMonthlyIncome, totalMonthlyExpenses, totalMonthlyIncome - totalMonthlyExpenses);
    }


    @Override
    public MonthlyAndYearlySummaryDto getYearlySummary(int id, int year) {
        List<IncomeSummaryDto> incomeList = incomeService.getAllIncomes(id);
        double totalMonthlyIncome = incomeList.stream()
                .filter(i -> {
                    LocalDate respectiveDate = i.date();
                    return respectiveDate.getYear() == year;
                })
                .mapToDouble(i -> i.amount())
                .sum();

        List<ExpenseSummaryDto> expenseList = expenseService.getAllExpenses(id);
        double totalMonthlyExpenses = expenseList.stream()
                .filter(i -> {
                    LocalDate respectiveDate = i.date();
                    return respectiveDate.getYear() == year;
                })
                .mapToDouble(i -> i.amount())
                .sum();

        return new MonthlyAndYearlySummaryDto(totalMonthlyIncome, totalMonthlyExpenses, totalMonthlyIncome - totalMonthlyExpenses);
    }
}
