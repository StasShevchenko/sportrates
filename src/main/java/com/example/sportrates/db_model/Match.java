package com.example.sportrates.db_model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long matchId;

    @Column(name = "match_name")
    private String matchName;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "firstPlayerName")
    private String firstPlayerName;

    @Column(name = "secondPlayerName")
    private String secondPlayerName;

    @Column(name = "first_coefficient")
    private double firstCoefficient;

    @Column(name = "second_coefficient")
    private double secondCoefficient;

    @Column(name = "result")
    private String result;

    @OneToMany
    @JoinColumn(name = "match_id", referencedColumnName = "matchId")
    private List<Rate> rates;

    public Match() {
        super();
    }

    public Match(String matchName, String gameName, String firstPlayerName, String secondPlayerName, double firstCoefficient, double secondCoefficient, String result) {
        super();
        this.matchName = matchName;
        this.gameName = gameName;
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.result = result;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    public void setFirstPlayerName(String firstPlayerName) {
        this.firstPlayerName = firstPlayerName;
    }

    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    public void setSecondPlayerName(String secondPlayerName) {
        this.secondPlayerName = secondPlayerName;
    }

    public double getFirstCoefficient() {
        return firstCoefficient;
    }

    public void setFirstCoefficient(double firstCoefficient) {
        this.firstCoefficient = firstCoefficient;
    }

    public double getSecondCoefficient() {
        return secondCoefficient;
    }

    public void setSecondCoefficient(double secondCoefficient) {
        this.secondCoefficient = secondCoefficient;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Rate> getRates(){return rates;}
}
