package com.example.sportrates.conroller;


import com.example.sportrates.db_model.Balance;
import com.example.sportrates.db_model.Match;
import com.example.sportrates.db_model.Rate;
import com.example.sportrates.db_model.User;
import com.example.sportrates.model.RateInfo;
import com.example.sportrates.repository.MatchRepository;
import com.example.sportrates.repository.RateRepository;
import com.example.sportrates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class RateController {

    @Autowired
    RateRepository rateRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/addrate")
    @CrossOrigin(origins = "*")
    public ResponseEntity addRate(
            @RequestBody Rate rate,
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "matchId") Long matchId
    ) {
        rate.setStatus("open");
        Match match = matchRepository.findById(matchId).get();
        if (match.getResult() != null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        User user = userRepository.findById(userId).get();
        rate.setMatch(match);
        rate.setUser(user);
        Balance balance = user.getBalance();
        balance.setOverallBalance(balance.getOverallBalance() - rate.getRateSum());
        balance.setRateBalance(balance.getRateBalance() + rate.getRateSum());
        rateRepository.save(rate);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/activematches")
    @CrossOrigin(origins = "*")
    public List<Match> getActiveMatches() {
        List<Match> matches = matchRepository.findAll();
        matches = matches.stream().filter(
                match -> match.getResult() == null
        ).toList();
        return matches;
    }

    @GetMapping("/rates")
    @CrossOrigin(origins = "*")
    public List<RateInfo> getUserRates(
            @RequestParam(name = "userId") Long userId
    ){
        List<RateInfo> rateInfoList = new ArrayList<>();
        List<Rate> rates = rateRepository.findByUserUserId(userId);
        for (Rate rate : rates) {
            rateInfoList.add(rate.mapToRateInfo());
        }
        return rateInfoList;
    }

}
