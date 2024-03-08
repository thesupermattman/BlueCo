package com.aamlid.blueco.repository;

import com.aamlid.blueco.entity.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BudgetRepository extends MongoRepository<Budget, String> {

    Budget findByType(String type);
}
