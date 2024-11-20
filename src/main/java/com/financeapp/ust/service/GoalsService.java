package com.financeapp.ust.service;

import com.financeapp.ust.dto.GoalsDto;
import com.financeapp.ust.dto.summaryDto.GoalsSummaryDto;

import java.util.List;

public interface GoalsService {

    GoalsDto save(int id, GoalsDto goalsDto);

    List<GoalsSummaryDto> getAllGoals(int userID);

    GoalsDto updateByIdAndGoalName(int userId, String goalName, GoalsDto goalsDto);

    void delete(int userId, String goalName);
}
