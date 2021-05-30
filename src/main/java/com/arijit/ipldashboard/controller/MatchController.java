package com.arijit.ipldashboard.controller;

import com.arijit.ipldashboard.Repository.MatchRepository;
import com.arijit.ipldashboard.entity.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/match")
@RestController
public class MatchController {

    @Autowired
    MatchRepository matchRepository;

    @GetMapping(path = "/{teamName}")
    public List<Match> findRecentMatchByTeamName(@PathVariable("teamName") String teamName){
        return matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName);
    }
}
