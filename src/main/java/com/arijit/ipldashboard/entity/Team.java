package com.arijit.ipldashboard.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@JsonPropertyOrder({
        "id",
        "teamName",
        "totalMatches",
        "matches",
        "totalWins"
})
@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    private Long totalMatches;
    @Transient
    private List<Match> matches;
    private Long totalWins;
}
