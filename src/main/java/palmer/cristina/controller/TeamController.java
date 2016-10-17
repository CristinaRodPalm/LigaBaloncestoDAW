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
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Team save(@RequestBody Team team){
        return teamRepository.save(team);
    }

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public List<Team> findAll(){
        List<Team> teams = new ArrayList<Team>();
        Iterator<Team> iterator = teamRepository.findAll().iterator();
        while(iterator.hasNext()){
            teams.add(iterator.next());
        }
        return teams;
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        Team team = teamRepository.findOne(id);
        //if(team == null) throw new TeamException(id);
        if (team != null) teamRepository.delete(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Team updateById(@PathVariable Long id, @RequestBody Team team){
        Team t = teamRepository.findOne(id);
        //if(t == null) throw new TeamException(id);
        if(t == team) return null;
        return teamRepository.save(team);
    }
}