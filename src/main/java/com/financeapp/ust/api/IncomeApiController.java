package com.financeapp.ust.api;

import com.financeapp.ust.dto.IncomeDto;
import com.financeapp.ust.dto.summaryDto.IncomeSummaryDto;
import com.financeapp.ust.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin("http://127.0.0.1:5500")
public class IncomeApiController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping("/{id}")
    public ResponseEntity<IncomeDto> save(@PathVariable("id") int id, @RequestBody IncomeDto incomeDto) {
        IncomeDto incomeDto1 = incomeService.save(id, incomeDto);
        if (incomeDto1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(incomeDto1); // 201
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<IncomeSummaryDto>> getAllIncomes(@PathVariable("user_id") int userId) {
        List<IncomeSummaryDto> list = incomeService.getAllIncomes(userId);
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(list); // 200
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404
        }
    }

    @PutMapping("/{id}/source")
    public ResponseEntity<IncomeDto> update(@PathVariable("id") int userId, @RequestParam("name") String source, @RequestBody IncomeDto incomeDto) {
        IncomeDto incomeDto1 = incomeService.updateById(userId, source, incomeDto);
        if (incomeDto1 != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(incomeDto1); // 202
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}/source")
    public ResponseEntity deleteByIdAndSource(@PathVariable("id") int userId, @RequestParam("name") String source) {
        incomeService.deleteByIdAndSource(userId, source);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); //204
    }
}
