package com.financeapp.ust.api;

import com.financeapp.ust.dto.ExpenseDto;
import com.financeapp.ust.dto.summaryDto.ExpenseSummaryDto;
import com.financeapp.ust.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin("http://127.0.0.1:5500")
public class ExpenseApiController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/{id}")
    public ResponseEntity<ExpenseDto> save(@PathVariable("id") int id, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto expenseDto1 = expenseService.save(id, expenseDto);
        if (expenseDto1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(expenseDto1);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<ExpenseSummaryDto>> getAllBudgets(@PathVariable("user_id") int userId) {
        List<ExpenseSummaryDto> list = expenseService.getAllExpenses(userId);
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{user_id}/category")
    public ResponseEntity<ExpenseDto> update(@PathVariable("user_id") int userId, @RequestParam("name") String category, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto expenseDto1 = expenseService.updateByIdAndCategory(userId, category, expenseDto);
        if (expenseDto1 != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(expenseDto1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{user_id}/category")
    public ResponseEntity delete(@PathVariable("user_id") int userId, @RequestParam("name") String category) {
        expenseService.delete(userId, category);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
