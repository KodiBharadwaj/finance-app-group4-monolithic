package com.financeapp.ust.service;

import com.financeapp.ust.dto.GoalsDto;
import com.financeapp.ust.dto.summaryDto.GoalsSummaryDto;
import com.financeapp.ust.model.Expense;
import com.financeapp.ust.model.Goals;
import com.financeapp.ust.model.User;
import com.financeapp.ust.repository.GoalsRepository;
import com.financeapp.ust.repository.UserRepository;
import com.financeapp.ust.util.ExpenseEntityCoversation;
import com.financeapp.ust.util.GoalsEntityConversion;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalsServiceImplementation implements GoalsService {

    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GoalsDto save(int id, GoalsDto goalsDto) {
//        Goals goals = GoalsEntityConversion.DtoToEntityCoversion(goalsDto);
//        User user = userRepository.findById(goals.getUser().getId()).get();
//        goals.setUser(user);
//        Goals goals1 = goalsRepository.save(goals);
//        return GoalsEntityConversion.EntityToDtoConversion(goals1);
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }

        Goals goals = GoalsEntityConversion.DtoToEntityCoversion(goalsDto);
        goals.setUser(user);
        user.getGoals().add(goals);

        goals = goalsRepository.save(goals);

        return GoalsEntityConversion.EntityToDtoConversion(goals);
    }

    @Override
    public List<GoalsSummaryDto> getAllGoals(int userID) {
        return goalsRepository.findAll()
                .stream()
                .filter(i -> i.getUser().getId() == userID)
                .map(i -> new GoalsSummaryDto(i.getId(), i.getGoalName(), i.getCurrentAmount(), i.getTargetAmount(), i.getDeadLine()))
                .toList();
    }

    @Override
    public GoalsDto updateByIdAndGoalName(int userId, String goalName, GoalsDto goalsDto) {
        Goals goals = goalsRepository.getGoalsByUserIdAndGoalName(userId, goalName);

        if (goals == null) {
            throw new EntityNotFoundException("Income with source " + goals + " not found for user ID " + userId);
        }
        if (goalsDto.currentAmount() > 0) {
            goals.setCurrentAmount(goalsDto.currentAmount());
        }
        if (goalsDto.targetAmount() > 0) {
            goals.setTargetAmount(goalsDto.targetAmount());
        }
        if (goalsDto.deadLine() != null) {
            goals.setDeadLine(goalsDto.deadLine());
        }

        //return save(userId, GoalsEntityConversion.EntityToDtoConversion(goals));
        Goals updatedGoals = goalsRepository.save(goals);
        return GoalsEntityConversion.EntityToDtoConversion(updatedGoals);
    }

    @Override
    public void delete(int userId, String goalName) {
        goalsRepository.deleteByUserIdAndGoalName(userId, goalName);
    }
}
