package com.financeapp.ust.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeapp.ust.model.Budget;
import com.financeapp.ust.model.Expense;
import com.financeapp.ust.model.Goals;
import com.financeapp.ust.model.Income;

import java.util.List;

public record UserDto(int id, String email, String phoneNo, String password, List<Income> incomes,
                      List<Expense> expenses, List<Budget> budgets, List<Goals> goals) {
}
