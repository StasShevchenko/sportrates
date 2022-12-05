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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
    public synchronized void finishMatch(
            @RequestParam(name = "id") Long id
    ) {
        Match match = matchRepository.findById(id).get();
        ArrayList<Rate> rateList = new ArrayList<>(match.getRates());
        int result = getRandomNumber(1, 3);
        double winCoefficient;
        if (result == 1) {
            match.setResult(match.getFirstPlayerName());
            winCoefficient = match.getFirstCoefficient();
        } else{
            match.setResult(match.getSecondPlayerName());
            winCoefficient = match.getSecondCoefficient();
        }
        ListIterator<Rate> itr = rateList.listIterator();
        while (itr.hasNext()) {
            Rate rate = itr.next();
            Balance userBalance = balanceRepository.findByUserUserId(rate.getUser().getUserId());
            if (Objects.equals(rate.getChoice(), match.getResult())) {
                 userBalance.setOverallBalance(userBalance.getOverallBalance() + (rate.getRateSum() * winCoefficient));
            } else {
                    userBalance.setOverallBalance(userBalance.getOverallBalance() - rate.getRateSum());
            }
            userBalance.setRateBalance(userBalance.getRateBalance() - rate.getRateSum());
            balanceRepository.saveAndFlush(userBalance);
            balanceRepository.flush();
            rate.setStatus("closed");
            rateRepository.save(rate);
            rateRepository.flush();
        }
        matchRepository.save(match);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
