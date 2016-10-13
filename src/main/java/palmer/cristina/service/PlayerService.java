package palmer.cristina.service;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import palmer.cristina.domain.Player;
import palmer.cristina.domain.Position;
import palmer.cristina.repository.PlayerRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Cristina on 10/10/2016.
 */
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;


    public void crearJugadores() {

        Player player1 = new Player("Paco", LocalDate.of(2001, 10, 10), 1, 11, 25, Position.GUARDS);
        playerRepository.save(player1);
        Player player2 = new Player("Juancho", LocalDate.of(2002, 10, 10), 2, 12, 26, Position.FORWARDS);
        playerRepository.save(player2);
        Player player3 = new Player("Deivi", LocalDate.of(2003, 10, 10), 3, 13, 27, Position.PIVOT);
        playerRepository.save(player3);
        Player player4 = new Player("Aivan", LocalDate.of(2004, 10, 10), 4, 14, 28, Position.CENTER);
        playerRepository.save(player4);
        Player player5 = new Player("Alesandro", LocalDate.of(2005, 10, 10), 5, 15, 29, Position.SHOOTER);
        playerRepository.save(player5);

    }

    public void test(){
        System.out.println(playerRepository.findByNameStartingWith("D"));
        System.out.println(playerRepository.findByBasketsGreaterThanEqual(4));
        System.out.println(playerRepository.findByBirthDateAfter(LocalDate.of(2003, 10, 12)));
        System.out.println(playerRepository.findByPositionEquals(Position.SHOOTER));
        List<Object[]> players = playerRepository.findPlayerByPositionQuery();
        for (Object[] player : players) {
            System.out.println(player[3]+", avg baskets: "+player[0]+", avg assists: "+player[1]+", avg rebound: "+player[2]);
        }
    }
}


