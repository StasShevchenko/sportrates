package com.example.sportrates.db_model;

import javax.persistence.*;

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long balanceId;

    @Column(name = "overall_balance")
    private int overallBalance;

    @Column(name = "rate_balance")
    private int rateBalance;

    public Balance(){
        super();
    }

    public Balance(int overallBalance, int rateBalance) {
        super();
        this.overallBalance = overallBalance;
        this.rateBalance = rateBalance;
    }

    public long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(long balanceId) {
        this.balanceId = balanceId;
    }

    public int getOverallBalance() {
        return overallBalance;
    }

    public void setOverallBalance(int overallBalance) {
        this.overallBalance = overallBalance;
    }

    public int getRateBalance() {
        return rateBalance;
    }

    public void setRateBalance(int rateBalance) {
        this.rateBalance = rateBalance;
    }
}
