package palmer.cristina.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;
import palmer.cristina.domain.Player;
import palmer.cristina.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    public Player createPlayer(@RequestBody Player player){
        return playerRepository.save(player);
    }

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public List<Player> findAllPlayers(){
        List<Player> players = new ArrayList<Player>();
        Iterator<Player> iterator = playerRepository.findAll().iterator();
        while(iterator.hasNext()){
            players.add(iterator.next());
        }
        return players;
    }

    // DELETE
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePlayerId(@PathVariable Long id){
        Player player = playerRepository.findOne(id);
        //if(player == null) throw new PlayerException(id);
        if(player != null) playerRepository.delete(id);
    }

    // PUT
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Player updatePlayerId(@PathVariable Long id, @RequestBody Player player){
        Player p = playerRepository.findOne(id);
        //if(p == null) throw new PlayerException(id);
        if(p == player) return null;
        return playerRepository.save(player);
    }

    // GET 1 PLAYER -> java8
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(millis = 1000)
    public ResponseEntity<Player> getPlayer(@PathVariable Long id){
        Player player = playerRepository.findOne(id);
        return Optional.ofNullable(player)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<Player>(HttpStatus.NOT_FOUND));
    }
}
