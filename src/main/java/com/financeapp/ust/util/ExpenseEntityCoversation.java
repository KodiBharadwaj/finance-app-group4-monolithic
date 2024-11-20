package com.financeapp.ust.util;

import com.financeapp.ust.dto.ExpenseDto;
import com.financeapp.ust.model.Expense;

public class ExpenseEntityCoversation {

    public static Expense DtoToEntityCoversation(ExpenseDto expenseDto) {
        //User user = UserEntityConversion.DtoToEntityConversion(expenseDto.user());
        Expense expense = new Expense(expenseDto.id(), expenseDto.category(), expenseDto.amount(), expenseDto.date(), expenseDto.user());
        return expense;
    }

    public static ExpenseDto EntityToDtoCoversion(Expense expense) {
        //UserDto userDto = UserEntityConversion.EntityToDtoConversion(expense.getUser());
        ExpenseDto expenseDto = new ExpenseDto(expense.getId(), expense.getCategory(), expense.getAmount(), expense.getDate(), expense.getUser());
        return expenseDto;
    }
}
