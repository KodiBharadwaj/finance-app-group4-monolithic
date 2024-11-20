package com.financeapp.ust.util;

import com.financeapp.ust.dto.UserDto;
import com.financeapp.ust.model.User;


public class UserEntityConversion {

    public static User DtoToEntityConversion(UserDto userDto) {
        User user = new User(userDto.id(), userDto.email(), userDto.phoneNo(), userDto.password(), userDto.incomes(), userDto.expenses(), userDto.budgets(), userDto.goals());
        return user;
    }

    public static UserDto EntityToDtoConversion(User user) {
//        List<IncomeDto> list = user.getIncomes().stream().map(i->IncomeEntityConversion.EntityToDtoConversation(i)).toList();
        UserDto userDto = new UserDto(user.getId(), user.getEmail(), user.getPhoneNo(), user.getPassword(), user.getIncomes(), user.getExpenses(), user.getBudgets(), user.getGoals());
        return userDto;
    }

}
