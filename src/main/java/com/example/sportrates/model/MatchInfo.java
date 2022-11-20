package com.example.sportrates.model;

import lombok.Data;

@Data
public class MatchInfo {
    private Long matchId;
    private String matchName;
    private String gameName;
    private String firstPlayerName;
    private String secondPlayerName;
    private double firstCoefficient;
    private double secondCoefficient;
    private String result;
}

