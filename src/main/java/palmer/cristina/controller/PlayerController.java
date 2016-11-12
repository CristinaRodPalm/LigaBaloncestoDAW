package palmer.cristina.controller;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // POST
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
        if(player != null) playerRepository.delete(id);
    }

    // PUT
    @PutMapping
    public Player updatePlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }
    // GET 1 PLAYER
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

    // GET --> AVG BASKETS, ASSISTS, REBOUND FROM ALL PLAYERS SAME POSITION
    @GetMapping("/byPosition")
    public Map<Position, Statistic> groupByPosition(){
        List<Object[]> players = playerRepository.groupByPosition();

        Map<Position, Statistic> posis = new HashMap<>();

        for (Object[] p: players) {
            Statistic aux = new Statistic((Position) p[0], (int) p[1], (int) p[2], (double) p[3]);
            posis.put(aux.getPosition(), aux);
        }
        return posis;
    }

    // GET --> SHOW ALL THE PLAYERS WITH THE SAME POSITION ORDERED BY BASKETS
    @GetMapping("/playersByPositionBaskets")
    public Map<Position, Collection<Player>> playersByPositionBaskets(){

        ListMultimap<Position, Player> playerMultiMap = ArrayListMultimap.create();

        playerRepository.playersByPositionBaskets().forEach(
                player -> playerMultiMap.put(player.getPosition(), player)
        );
        /*List<Player> players = playerRepository.playersByPositionBaskets();

        for( Player p : players){
            playerMultiMap.put(p.getPosition(), p);
        }*/

        return playerMultiMap.asMap();
    }

    // GET --> ORDER PLAYERS BY PARAM
    @GetMapping("/byParams")
    public List<Player> orderByParam(@RequestParam(name = "orderBy") String orderBy){
        if(orderBy != null) {
            return playerRepository.findAll(new Sort(Sort.Direction.DESC, orderBy));
        }
        return playerRepository.findAll();
    }
}
