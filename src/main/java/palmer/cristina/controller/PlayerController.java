package palmer.cristina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import palmer.cristina.domain.Player;
import palmer.cristina.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Cristina on 16/10/2016.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    // POST
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Player save(@RequestBody Player player){
        return playerRepository.save(player);
    }

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public List<Player> findAll(){
        List<Player> players = new ArrayList<Player>();
        Iterator<Player> iterator = playerRepository.findAll().iterator();
        while(iterator.hasNext()){
            players.add(iterator.next());
        }
        return players;
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id){
        Player player = playerRepository.findOne(id);
        //if(player == null) throw new PlayerException(id);
        if(player != null) playerRepository.delete(id);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Player updateById(@PathVariable Long id, @RequestBody Player player){
        Player p = playerRepository.findOne(id);
        //if(p == null) throw new PlayerException(id);
        if(p == player) return null;
        return playerRepository.save(player);
    }

}
