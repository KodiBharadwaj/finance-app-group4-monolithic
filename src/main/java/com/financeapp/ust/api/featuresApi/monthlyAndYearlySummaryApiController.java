package com.financeapp.ust.api.featuresApi;

import com.financeapp.ust.dto.featuresDto.MonthlyAndYearlySummaryDto;
import com.financeapp.ust.service.summary.MonthlyAndYearlySummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summary")
public class monthlyAndYearlySummaryApiController {

    @Autowired
    private MonthlyAndYearlySummary monthlyAndYearlySummary;

    @GetMapping("/monthly/{id}/{month}/{year}")
    public ResponseEntity<MonthlyAndYearlySummaryDto> getMonthlyInsights(@PathVariable("id") int id, @PathVariable("month") int month, @PathVariable("year") int year) {
        MonthlyAndYearlySummaryDto monthlySummaryDto = monthlyAndYearlySummary.getMonthlySummary(id, month, year);
        if (monthlySummaryDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(monthlySummaryDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/yearly/{id}/{year}")
    public ResponseEntity<MonthlyAndYearlySummaryDto> getYearlyInsights(@PathVariable("id") int id, @PathVariable("year") int year) {
        MonthlyAndYearlySummaryDto yearlySummaryDto = monthlyAndYearlySummary.getYearlySummary(id, year);
        if (yearlySummaryDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(yearlySummaryDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
