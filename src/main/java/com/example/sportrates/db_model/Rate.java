package com.example.sportrates.db_model;


import com.example.sportrates.model.RateInfo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rateId;

    @Column(name = "choice")
    private String choice;

    @Column(name = "rate_sum")
    private int rateSum;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "matchId")
    private Match match;

    public Rate() {
        super();
    }

    public Rate(String choice, int rateSum, String status) {
        this.choice = choice;
        this.rateSum = rateSum;
        this.status = status;
    }



    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getRateSum() {
        return rateSum;
    }

    public void setRateSum(int rateSum) {
        this.rateSum = rateSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setMatch(Match match){
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }

    public RateInfo mapToRateInfo(){
        RateInfo rateInfo = new RateInfo();
        rateInfo.setChoice(choice);
        rateInfo.setRateSum(rateSum);
        rateInfo.setMatchName(match.getMatchName());
        if (Objects.equals(choice, match.getFirstPlayerName())) {
            rateInfo.setCoefficient(match.getFirstCoefficient());
        }
        else{
            rateInfo.setCoefficient(match.getSecondCoefficient());
        }
        if (Objects.equals(status, "open")) {
            rateInfo.setStatus("open");
            match.setResult(null);
        }
        else {
            rateInfo.setStatus("closed");
            if (Objects.equals(choice, match.getResult())) {
                rateInfo.setResult(1);
            }
            else{
                rateInfo.setResult(0);
            }
        }
         return rateInfo;
    }
}
