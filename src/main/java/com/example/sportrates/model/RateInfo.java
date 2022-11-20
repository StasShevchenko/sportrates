package com.example.sportrates.model;

import lombok.Data;

@Data
public class RateInfo {
    private String matchName;
    private String choice;
    private double rateSum;
    private double coefficient;
    private String status;
    private Integer result;
}
