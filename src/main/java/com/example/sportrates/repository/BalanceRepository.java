package com.example.sportrates.repository;

import com.example.sportrates.db_model.Balance;
import com.example.sportrates.db_model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findByUserUserId(Long userId);
}
