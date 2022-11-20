package com.example.sportrates.db_model;

import javax.persistence.*;

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long balanceId;

    @Column(name = "overall_balance")
    private double overallBalance;

    @Column(name = "rate_balance")
    private double rateBalance;

    @OneToOne(mappedBy = "balance")
    private User user;


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

    public double getOverallBalance() {
        return overallBalance;
    }

    public void setOverallBalance(double overallBalance) {
        this.overallBalance = overallBalance;
    }

    public double getRateBalance() {
        return rateBalance;
    }

    public void setRateBalance(double rateBalance) {
        this.rateBalance = rateBalance;
    }
}
