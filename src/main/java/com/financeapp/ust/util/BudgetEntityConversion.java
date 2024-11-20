package com.financeapp.ust.util;

import com.financeapp.ust.dto.BudgetDto;
import com.financeapp.ust.dto.UserDto;
import com.financeapp.ust.model.Budget;
import com.financeapp.ust.model.User;

public class BudgetEntityConversion {

    public static Budget DtoToEntityConversion(BudgetDto budgetDto) {
        //User user = UserEntityConversion.DtoToEntityConversion(budgetDto.userDto());
        Budget budget = new Budget(budgetDto.id(), budgetDto.category(), budgetDto.currentSpending(), budgetDto.moneyLimit(), budgetDto.user());
        return budget;
    }

    public static BudgetDto EntityToDtoConversion(Budget budget) {
        //UserDto userDto = UserEntityConversion.EntityToDtoConversion(budget.getUser());
        BudgetDto budgetDto = new BudgetDto(budget.getId(), budget.getCategory(), budget.getCurrentSpending(), budget.getMoneyLimit(), budget.getUser());
        return budgetDto;
    }
}
