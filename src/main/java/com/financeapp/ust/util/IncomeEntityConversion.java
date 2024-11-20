package com.financeapp.ust.util;

import com.financeapp.ust.dto.IncomeDto;
import com.financeapp.ust.dto.UserDto;
import com.financeapp.ust.model.Income;
import com.financeapp.ust.model.User;

public class IncomeEntityConversion {

    public static Income DtoToEntityConversion(IncomeDto incomeDto) {
//        User user = UserEntityConversion.DtoToEntityConversion(incomeDto.userDto());
        Income income = new Income(incomeDto.id(), incomeDto.amount(), incomeDto.source(), incomeDto.date(), incomeDto.user());
        return income;
    }

    public static IncomeDto EntityToDtoConversation(Income income) {
        //UserDto userDto = UserEntityConversion.EntityToDtoConversion(income.getUser());
        IncomeDto incomeDto = new IncomeDto(income.getId(), income.getAmount(), income.getSource(), income.getDate(), income.getUser());
        return incomeDto;
    }

}
