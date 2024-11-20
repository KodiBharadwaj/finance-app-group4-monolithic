package com.financeapp.ust.api;

import com.financeapp.ust.dto.BudgetDto;
import com.financeapp.ust.dto.ExpenseDto;
import com.financeapp.ust.dto.summaryDto.BudgetSummaryDto;
import com.financeapp.ust.repository.BudgetRepository;
import com.financeapp.ust.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
@CrossOrigin("http://127.0.0.1:5500")
public class BudgetApiController {

    @Autowired
    private BudgetService budgetService;


    @PostMapping("/{id}")
    public ResponseEntity<BudgetDto> save(@PathVariable("id") int id, @RequestBody BudgetDto budgetDto) {
        BudgetDto budgetDto1 = budgetService.save(id, budgetDto);
        if (budgetDto1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(budgetDto1);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<BudgetSummaryDto>> getAllBudgets(@PathVariable("user_id") int userId) {
        List<BudgetSummaryDto> list = budgetService.getAllBudgets(userId);
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{user_id}/category")
    public ResponseEntity<BudgetDto> update(@PathVariable("user_id") int userId, @RequestParam("name") String category, @RequestBody BudgetDto budgetDto) {
        BudgetDto budgetDto1 = budgetService.updateByIdAndCategory(userId, category, budgetDto);
        if (budgetDto1 != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(budgetDto1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{user_id}/category")
    public ResponseEntity delete(@PathVariable("user_id") int userId, @RequestParam("name") String category) {
        budgetService.delete(userId, category);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
