package com.financeapp.ust.api;

import com.financeapp.ust.dto.GoalsDto;
import com.financeapp.ust.dto.summaryDto.GoalsSummaryDto;
import com.financeapp.ust.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin("http://127.0.0.1:5500")
public class GoalsApiRepository {

    @Autowired
    private GoalsService goalsService;

    @PostMapping("/{id}")
    public ResponseEntity<GoalsDto> save(@PathVariable("id") int id, @RequestBody GoalsDto goalsDto) {
        GoalsDto goalsDto1 = goalsService.save(id, goalsDto);
        if (goalsDto1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(goalsDto1);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<GoalsSummaryDto>> getAllgoals(@PathVariable("user_id") int userId) {
        List<GoalsSummaryDto> list = goalsService.getAllGoals(userId);
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{user_id}/goalName")
    public ResponseEntity<GoalsDto> update(@PathVariable("user_id") int userId, @RequestParam("name") String goalName, @RequestBody GoalsDto goalsDto) {
        GoalsDto goalsDto1 = goalsService.updateByIdAndGoalName(userId, goalName, goalsDto);
        if (goalsDto1 != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(goalsDto1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{user_id}/goalName")
    public ResponseEntity delete(@PathVariable("user_id") int userId, @RequestParam("name") String goalName) {
        goalsService.delete(userId, goalName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
