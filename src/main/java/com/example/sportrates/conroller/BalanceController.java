package com.example.sportrates.conroller;

import com.example.sportrates.db_model.Balance;
import com.example.sportrates.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @Autowired
    BalanceRepository balanceRepository;

    @PutMapping("/addmoney")
    @CrossOrigin(origins = "*")
    public void addMoneyToBalance(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "money") Integer moneyAmount
    ){
        Balance balance = balanceRepository.findByUserUserId(userId);
        balance.setOverallBalance(balance.getOverallBalance() + moneyAmount);
        balanceRepository.save(balance);
    }
}
