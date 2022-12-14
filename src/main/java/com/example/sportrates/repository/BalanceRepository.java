package com.example.sportrates.repository;

import com.example.sportrates.db_model.Balance;
import com.example.sportrates.db_model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    Balance findByUserUserId(Long userId);
}
