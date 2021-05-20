package com.arijit.ipldashboard.util;

import com.arijit.ipldashboard.entity.Match;
import com.arijit.ipldashboard.entity.MatchInput;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.valueOf(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDateTime.parse(matchInput.getDate(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        match.setVenue(match.getVenue());

        if("bat".equals(matchInput.getToss_winner())){
            match.setTeam1(matchInput.getToss_winner());
            match.setTeam2(match.getTeam1()
                    .equals(matchInput.getTeam1())?matchInput.getTeam2()
                    :matchInput.getTeam1());
        }else{
            match.setTeam2(matchInput.getToss_winner());
            match.setTeam1(match.getTeam2()
                    .equals(matchInput.getTeam2())?matchInput.getTeam1()
                    :matchInput.getTeam2());
        }

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setMethod(matchInput.getMethod());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;
    }
}
