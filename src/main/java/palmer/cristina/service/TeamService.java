package palmer.cristina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import palmer.cristina.domain.Position;
import palmer.cristina.domain.Team;
import palmer.cristina.repository.TeamRepository;

import java.time.LocalDate;

/**
 * Created by Cristina on 13/10/2016.
 */
@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public void createTeam(){
        Team team1 = new Team("team1", "Barcelona", LocalDate.of(2010, 01, 20));
        teamRepository.save(team1);
        Team team2 = new Team("team2", "Madrid", LocalDate.of(2009, 02, 20));
        teamRepository.save(team2);
        Team team3 = new Team("team3", "Barcelona", LocalDate.of(2008, 02, 20));
        teamRepository.save(team3);
    }

    public void testTeam(){
        System.out.println(teamRepository.findTeamByCity("Madrid"));
        teamRepository.findPlayersByTeamName("team2").forEach(player -> System.out.println(player));
        teamRepository.findPlayersByTeamNameAndPosition("team2", Position.CENTER).forEach(player -> System.out.println(player));
        System.out.println(teamRepository.findMoreBasketsByTeam("team2").get(0));
        //List<Player> aux = teamRepository.findMoreBasketsByTeam("team2");
        //System.out.println(aux.get(0));
    }

}
