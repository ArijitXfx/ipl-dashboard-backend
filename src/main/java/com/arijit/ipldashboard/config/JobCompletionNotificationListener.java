package com.arijit.ipldashboard.config;

import com.arijit.ipldashboard.entity.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("=====Finish importing all data=====");
            Map<String, Team> teamMap = new HashMap<>();
            em.createQuery("select team1, count(team1) from Match group by team1", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e->{
                        Team team = new Team();
                        team.setTeamName((String)e[0]);
                        team.setTotalMatches((Long) e[1]);
                        teamMap.put(team.getTeamName(), team);
                    });
            em.createQuery("select team2, count(team2) from Match group by team2", Object[].class)
                    .getResultList()
                    .stream()
                    .forEach(e ->{
                        Team team = teamMap.get((String) e[0]);
                        if(team!=null)
                            teamMap.put(team.getTeamName(),team);
                    });

            teamMap.keySet().forEach(key->{
                Team team = teamMap.get(key);
                Long totalWins = (Long) em.createQuery("select count(*) from Match where matchWinner=?1")
                        .setParameter(1,team.getTeamName())
                        .getSingleResult();
                team.setTotalWins(totalWins);
                em.persist(team);
            });

            log.info("====Match Details====");
            em.createQuery("select id, team1, team2, matchWinner from Match", Object[].class)
                    .setMaxResults(5)
                    .getResultList()
                    .stream()
                    .forEach(e-> System.out.println(Arrays.toString(e)));

            log.info("====Team Details====");
            em.createQuery("select id, teamName, totalMatches, totalWins from Team", Object[].class)
                    .setMaxResults(5)
                    .getResultList()
                    .stream()
                    .forEach(e-> System.out.println(Arrays.toString(e)));
        }
    }
}
