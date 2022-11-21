package com.example.sportrates.conroller;

import com.example.sportrates.db_model.Balance;
import com.example.sportrates.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getbalance")
    @CrossOrigin(origins = "*")
    public Balance getUserBalance(
            @RequestParam(name = "userId") Long userId
    ){
        return balanceRepository.findByUserUserId(userId);
    }
}
