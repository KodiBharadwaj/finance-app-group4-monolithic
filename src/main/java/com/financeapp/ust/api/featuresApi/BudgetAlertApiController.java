package com.financeapp.ust.api.featuresApi;

import com.financeapp.ust.dto.featuresDto.BudgetAlertDto;
import com.financeapp.ust.service.BudgetService;
import com.financeapp.ust.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/alert")
public class BudgetAlertApiController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping("/{id}")
    public ResponseEntity<List<BudgetAlertDto>> getBudgetAlerts(@PathVariable("id") int id) {
        List<BudgetAlertDto> list = budgetService.getBudgetAlerts(id);
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
