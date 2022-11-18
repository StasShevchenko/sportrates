package com.example.sportrates.conroller;


import com.example.sportrates.exception.ResourceNotFoundException;
import com.example.sportrates.db_model.Match;
import com.example.sportrates.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    //get matches
    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return this.matchRepository.findAll();
    }

    //get matches by id
    @GetMapping("/matches/{id}")
    public ResponseEntity<Match> getMatchById(
            @PathVariable(value = "id") Long matchId
    ) throws ResourceNotFoundException {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("not Found"));
        return ResponseEntity.ok().body(match);

    }


    //save match
    @PostMapping("/addmatch")
    public Match createMatch(@RequestBody Match match) {
        return this.matchRepository.save(match);
    }

    //update match
    @PutMapping("/finishmatch/{id}")
    public ResponseEntity<Match> finishMatch(
            @PathVariable(value = "id") Long matchId,
            @RequestParam String result
    ) throws ResourceNotFoundException {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        match.setResult(result);
        return ResponseEntity.ok(this.matchRepository.save(match));
    }

    //delete match

}
