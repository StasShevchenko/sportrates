package com.example.sportrates.db_model;


import javax.persistence.*;

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
}
