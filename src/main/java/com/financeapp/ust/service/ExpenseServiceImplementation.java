package com.financeapp.ust.service;

import com.financeapp.ust.dto.ExpenseDto;
import com.financeapp.ust.dto.featuresDto.BudgetAlertDto;
import com.financeapp.ust.dto.summaryDto.ExpenseSummaryDto;
import com.financeapp.ust.model.Budget;
import com.financeapp.ust.model.Expense;
import com.financeapp.ust.model.Income;
import com.financeapp.ust.model.User;
import com.financeapp.ust.repository.BudgetRepository;
import com.financeapp.ust.repository.ExpenseRepository;
import com.financeapp.ust.repository.UserRepository;
import com.financeapp.ust.util.ExpenseEntityCoversation;
import com.financeapp.ust.util.IncomeEntityConversion;
import com.financeapp.ust.util.UserEntityConversion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImplementation implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ExpenseDto save(int id, ExpenseDto expenseDto) {
//        Expense expense = ExpenseEntityCoversation.DtoToEntityCoversation(expenseDto);
//        User user = userRepository.findById(expense.getUser().getId()).get();
//        expense.setUser(user);
//        Expense expense1 = expenseRepository.save(expense);
//        return ExpenseEntityCoversation.EntityToDtoCoversion(expense1);

        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }

        Expense expense = ExpenseEntityCoversation.DtoToEntityCoversation(expenseDto);
        expense.setUser(user);
        user.getExpenses().add(expense);

        expense = expenseRepository.save(expense);

        return ExpenseEntityCoversation.EntityToDtoCoversion(expense);
    }

    @Override
    public List<ExpenseSummaryDto> getAllExpenses(int userId) {
        return expenseRepository.findAll()
                .stream()
                .filter(i -> i.getUser().getId() == userId)
                .map(i -> new ExpenseSummaryDto(i.getId(), i.getCategory(), i.getAmount(), i.getDate()))
                .toList();
    }

    @Override
    public ExpenseDto updateByIdAndCategory(int userId, String category, ExpenseDto expenseDto) {
        Expense expense = expenseRepository.findByuserIdAndCategory(userId, category);
        if (expense == null) {
            throw new EntityNotFoundException("Income with source " + expense + " not found for user ID " + userId);
        }
        if (expenseDto.amount() > 0) {
            expense.setAmount(expenseDto.amount());
        }
        if (expenseDto.date() != null) {
            expense.setDate(expenseDto.date());
        }

        //return save(userId, ExpenseEntityCoversation.EntityToDtoCoversion(expense));
        Expense updatedExpense = expenseRepository.save(expense);
        return ExpenseEntityCoversation.EntityToDtoCoversion(updatedExpense);
    }

    @Override
    public void delete(int userId, String category) {
        expenseRepository.deleteByIdAndCategory(userId, category);
    }

}
