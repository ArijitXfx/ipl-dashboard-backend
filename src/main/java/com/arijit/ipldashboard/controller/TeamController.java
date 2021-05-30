package com.arijit.ipldashboard.controller;

import com.arijit.ipldashboard.Repository.MatchRepository;
import com.arijit.ipldashboard.Repository.TeamRepository;
import com.arijit.ipldashboard.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    @GetMapping(path = "/{teamName}")
    public Team findTeam(@PathVariable("teamName") String teamName){

        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findByTeam1OrTeam2OrderByDateDesc(team.getTeamName(),team.getTeamName()));
        return team;
    }
}
