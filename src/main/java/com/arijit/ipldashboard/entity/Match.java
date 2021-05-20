package com.arijit.ipldashboard.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Match {
    private Long id;
    private String city;
    private LocalDateTime date;
    private String playerOfMatch;
    private String venue;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String matchWinner;
    private String result;
    private String resultMargin;
    private String method;
    private String umpire1;
    private String umpire2;
}