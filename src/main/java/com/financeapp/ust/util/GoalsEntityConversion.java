package com.financeapp.ust.util;

import com.financeapp.ust.dto.GoalsDto;
import com.financeapp.ust.dto.UserDto;
import com.financeapp.ust.model.Goals;
import com.financeapp.ust.model.User;

public class GoalsEntityConversion {

    public static Goals DtoToEntityCoversion(GoalsDto goalsDto) {
        //User user = UserEntityConversion.DtoToEntityConversion(goalsDto.userDto());
        Goals goals = new Goals(goalsDto.id(), goalsDto.goalName(), goalsDto.currentAmount(), goalsDto.targetAmount(), goalsDto.deadLine(), goalsDto.user());
        return goals;
    }

    public static GoalsDto EntityToDtoConversion(Goals goals) {
        //UserDto userDto = UserEntityConversion.EntityToDtoConversion(goals.getUser());
        GoalsDto goalsDto = new GoalsDto(goals.getId(), goals.getGoalName(), goals.getCurrentAmount(), goals.getTargetAmount(), goals.getDeadLine(), goals.getUser());
        return goalsDto;
    }
}
