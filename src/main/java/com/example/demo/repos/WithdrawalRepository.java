package com.example.demo.repos;

import com.example.demo.models.Withdrawal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WithdrawalRepository extends MongoRepository<Withdrawal, String> {
    Optional<Withdrawal> findById(String id);
}
