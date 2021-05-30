package com.arijit.ipldashboard.Repository;

import com.arijit.ipldashboard.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    public List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2);
}
