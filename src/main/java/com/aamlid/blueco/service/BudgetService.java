package com.aamlid.blueco.service;

import com.aamlid.blueco.entity.Budget;
import com.aamlid.blueco.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget update(String type, Budget budget) {
        Optional<Budget> optionalBudget = Optional.ofNullable(budgetRepository.findByType(type));

        if (optionalBudget.isPresent()) {
            Budget existingbudget = optionalBudget.get();

            existingbudget.setType(budget.getType());
            existingbudget.setAmount(budget.getAmount());
            return budgetRepository.save(existingbudget);
        } else {
            return null;
        }
    }
}
