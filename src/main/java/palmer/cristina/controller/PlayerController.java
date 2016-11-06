package palmer.cristina.controller;

import org.hibernate.type.AnyType;
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
import palmer.cristina.domain.Position;
import palmer.cristina.domain.Statistic;
import palmer.cristina.repository.PlayerRepository;

import java.util.*;

/**
 * Created by Cristina on 16/10/2016.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Player createPlayer(@RequestBody Player player){
        return playerRepository.save(player);
    }

    // GET
    @GetMapping
    public List<Player> getAllPlayer(){
        List<Player> players = new ArrayList<Player>();
        Iterator<Player> iterator = playerRepository.findAll().iterator();
        while(iterator.hasNext()){
            players.add(iterator.next());
        }
        return players;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletePlayerID(@PathVariable Long id){
        Player player = playerRepository.findOne(id);
        //if(player == null) throw new PlayerException(id);
        if(player != null) playerRepository.delete(id);
    }

    // PUT
    @PutMapping("/{id}")
    public Player updatePlayerID(@PathVariable Long id, @RequestBody Player player){
        Player p = playerRepository.findOne(id);
        //if(p == null) throw new PlayerException(id);
        if(p == player) return null;
        return playerRepository.save(player);
    }

    // PUT
    @PutMapping
    public Player updatePlayer(@RequestBody Player player){
        return playerRepository.save(player);
    }

    // GET 1 PLAYER -
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerID(@PathVariable Long id){
        Player player = playerRepository.findOne(id);
        return Optional.ofNullable(player)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<Player>(HttpStatus.NOT_FOUND));
    }

    // GET --> ORDER BY BASKETS DESC
    @GetMapping("/byBaskets")
    public List<Player> orderByBaskets() {
        return playerRepository.orderByBaskets();
    }

    // GET --> BASKETS GREATHER THAN EQUAL
    @GetMapping("/byBaskets/{num}")
    public List<Player> findByBasketsGreaterThanEqual(@PathVariable Integer num) {
        return playerRepository.findByBasketsGreaterThanEqual(num);
    }

    // GET --> BASKETS BETWEEN
    @GetMapping("/byBaskets/{min}/{max}")
    public List<Player> findByBasketsBetween(@PathVariable Integer min, @PathVariable Integer max) {
        return playerRepository.findByBasketsBetween(min, max);
    }

    // Get
    @GetMapping("/byPosition")
    public Map<Position, Statistic> groupByPosition(){
        List<Object[]> jugs = playerRepository.groupByPosition();

        Map<Position, Statistic> posis = new HashMap<>();

        for (Object[] p: jugs) {
            Statistic aux = new Statistic((Position) p[0], (double) p[1], (double) p[2], (double) p[3]);
            posis.put(aux.getPosition(), aux);
        }
        return posis;
    }
}
