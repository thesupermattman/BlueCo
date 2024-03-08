package com.aamlid.blueco.controller;

import com.aamlid.blueco.entity.Budget;
import com.aamlid.blueco.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PutMapping("/budget/{type}")
    public ResponseEntity<Budget> updateBudget(@PathVariable String type, @RequestBody Budget budget) {
        Budget existingBudget = budgetService.update(type, budget);

        if (existingBudget != null) {
            return new ResponseEntity<>(budget, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
