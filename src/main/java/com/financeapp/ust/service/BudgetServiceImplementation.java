package com.financeapp.ust.service;

import com.financeapp.ust.dto.BudgetDto;
import com.financeapp.ust.dto.featuresDto.BudgetAlertDto;
import com.financeapp.ust.dto.summaryDto.BudgetSummaryDto;
import com.financeapp.ust.model.Budget;
import com.financeapp.ust.model.User;
import com.financeapp.ust.repository.BudgetRepository;
import com.financeapp.ust.repository.UserRepository;
import com.financeapp.ust.util.BudgetEntityConversion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetServiceImplementation implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public BudgetDto save(int id, BudgetDto budgetDto) {
//        Budget budget = BudgetEntityConversion.DtoToEntityConversion(budgetDto);
//        User user = userRepository.findById(budget.getUser().getId()).get();
//        budget.setUser(user);
//        Budget budget1 = budgetRepository.save(budget);
//        return BudgetEntityConversion.EntityToDtoConversion(budget1);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }

        Budget budget = BudgetEntityConversion.DtoToEntityConversion(budgetDto);
        budget.setUser(user);
        user.getBudgets().add(budget);

        budget = budgetRepository.save(budget);

        return BudgetEntityConversion.EntityToDtoConversion(budget);
    }

    @Override
    public List<BudgetSummaryDto> getAllBudgets(int userId) {
        return budgetRepository.findAll()
                .stream()
                .filter(i -> i.getUser().getId() == userId)
                .map(i -> new BudgetSummaryDto(i.getId(), i.getCategory(), i.getCurrentSpending(), i.getMoneyLimit()))
                .toList();
    }

    @Override
    public BudgetDto updateByIdAndCategory(int userId, String category, BudgetDto budgetDto) {
        Budget budget = budgetRepository.findByuserIdAndCategory(userId, category);
        if (budget == null) {
            throw new EntityNotFoundException("Income with source " + budget + " not found for user ID " + userId);
        }
        if (budgetDto.currentSpending() > 0) {
            budget.setCurrentSpending(budgetDto.currentSpending());
        }
        if (budgetDto.moneyLimit() > 0) {
            budget.setMoneyLimit(budgetDto.moneyLimit());
        }

        //return save(userId, BudgetEntityConversion.EntityToDtoConversion(budget));
        Budget updatedBudget = budgetRepository.save(budget);
        return BudgetEntityConversion.EntityToDtoConversion(updatedBudget);
    }

    @Override
    public void delete(int userId, String category) {
        budgetRepository.deleteByuserIdAndCategory(userId, category);
    }

    @Override
    public List<BudgetAlertDto> getBudgetAlerts(int id) {
        List<Budget> budgets = budgetRepository.findBudgetsByUserId(id);

        List<BudgetAlertDto> alerts = new ArrayList<>();
        for (Budget budget : budgets) {
            String category = budget.getCategory();
            double currentSpending = budget.getCurrentSpending();
            double moneyLimit = budget.getMoneyLimit();

            if (currentSpending > moneyLimit) {
                alerts.add(new BudgetAlertDto(category, currentSpending, moneyLimit, "Limit crossed!"));
            } else if (currentSpending >= moneyLimit * 0.9) {
                alerts.add(new BudgetAlertDto(category, currentSpending, moneyLimit, "Limit approaching!"));
            } else if (currentSpending == moneyLimit) {
                alerts.add(new BudgetAlertDto(category, currentSpending, moneyLimit, "Current Spending and Limit are equal"));
            } else {
                alerts.add(new BudgetAlertDto(category, currentSpending, moneyLimit, "Current spending is lesser than the limit"));
            }
        }

        return alerts;
    }
}
