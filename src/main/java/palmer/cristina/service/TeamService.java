package palmer.cristina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void testTeam(){
        System.out.println(teamRepository.findTeamByCity("Madrid"));
    }

}
