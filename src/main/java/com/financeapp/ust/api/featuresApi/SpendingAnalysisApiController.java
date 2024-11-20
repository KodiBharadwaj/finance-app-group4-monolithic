package com.financeapp.ust.api.featuresApi;

import com.financeapp.ust.dto.featuresDto.SpendingPatternDto;
import com.financeapp.ust.service.summary.SpendingAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class SpendingAnalysisApiController {

    @Autowired
    private SpendingAnalysisService spendingAnalysisService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<SpendingPatternDto>> getSpendingPattern(@PathVariable int userId) {
        List<SpendingPatternDto> insights = spendingAnalysisService.analyzeSpendingPattern(userId);
        if (!insights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(insights);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
