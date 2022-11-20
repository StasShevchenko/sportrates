package com.example.sportrates.conroller;


import com.example.sportrates.db_model.Balance;
import com.example.sportrates.db_model.Match;
import com.example.sportrates.db_model.Rate;
import com.example.sportrates.model.MatchInfo;
import com.example.sportrates.repository.BalanceRepository;
import com.example.sportrates.repository.MatchRepository;
import com.example.sportrates.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private BalanceRepository balanceRepository;


    //get matches
    @GetMapping("/matches")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public List<MatchInfo> getAllMatches() {
        return this.matchRepository.findAll().stream().map(match -> match.mapToMatchInfo()).toList();
    }

    //save match
    @PostMapping("/addmatch")
    @CrossOrigin(origins = "*")
    public Match createMatch(@RequestBody Match match) {
        match.setResult(null);
        return this.matchRepository.save(match);
    }

    //delete match
    @PutMapping("/deletematch")
    @CrossOrigin(origins = "*")
    public void deleteMatch(
            @RequestParam(name = "id") Long id
    ){
        matchRepository.deleteById(id);
    }

    //update match
    @PutMapping("/finishmatch")
    @CrossOrigin(origins = "*")
    public void finishMatch(
            @RequestParam(name = "id") Long id
    ) {
        Match match = matchRepository.findById(id).get();
        List<Rate> rateList = match.getRates();
        int result = getRandomNumber(1, 2);
        double winCoefficient;
        if (result == 1) {
            match.setResult(match.getFirstPlayerName());
            winCoefficient = match.getFirstCoefficient();
        } else{
            match.setResult(match.getSecondPlayerName());
            winCoefficient = match.getSecondCoefficient();
        }
        for (Rate rate : rateList) {
            Balance userBalance = balanceRepository.findByUserUserId(rate.getUser().getUserId());
            if (Objects.equals(rate.getChoice(), match.getResult())) {
                 userBalance.setOverallBalance(userBalance.getOverallBalance() + (rate.getRateSum() * winCoefficient));
            } else {
                    userBalance.setOverallBalance(userBalance.getOverallBalance() - rate.getRateSum());
            }
            userBalance.setRateBalance(userBalance.getRateBalance() - rate.getRateSum());
            balanceRepository.save(userBalance);
            rate.setStatus("closed");
            rateRepository.save(rate);
        }
        matchRepository.save(match);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
