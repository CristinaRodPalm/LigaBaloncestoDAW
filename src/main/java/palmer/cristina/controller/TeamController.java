package palmer.cristina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import palmer.cristina.domain.Team;
import palmer.cristina.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cristina on 16/10/2016.
 */
@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@RequestBody Team team){
        return teamRepository.save(team);
    }

    // GET
    @GetMapping
    public List<Team> getAllTeams(){
        List<Team> teams = new ArrayList<Team>();
        Iterator<Team> iterator = teamRepository.findAll().iterator();
        while(iterator.hasNext()){
            teams.add(iterator.next());
        }
        return teams;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteTeamID(@PathVariable Long id) {
        Team team = teamRepository.findOne(id);
        if (team != null) teamRepository.delete(id);
    }

    // PUT
    @PutMapping("/{id}")
    public Team updateTeamID(@PathVariable Long id, @RequestBody Team team){
        Team t = teamRepository.findOne(id);
        if(t == team) return null;
        return teamRepository.save(team);
    }
}