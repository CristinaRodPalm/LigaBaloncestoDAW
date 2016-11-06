package palmer.cristina.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import palmer.cristina.controller.PlayerController;
import palmer.cristina.domain.Player;
import palmer.cristina.domain.Position;
import palmer.cristina.domain.Team;
import palmer.cristina.repository.PlayerRepository;
import palmer.cristina.repository.TeamRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cristina on 10/10/2016.
 */
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;

    public void crearJugadores() {
        // Buscar equipos
        Team team1 = teamRepository.findOne(1L);
        Team team2 = teamRepository.findOne(2L);
        Team team3 = teamRepository.findOne(3L);

        //Creando jugadores
        Player player1 = new Player("Paco", LocalDate.of(2001, 10, 10), 1, 11, 25, Position.GUARDS, team1);
        playerRepository.save(player1);

        Player player2 = new Player("Juancho", LocalDate.of(2002, 10, 10), 2, 12, 26, Position.CENTER, team2);
        playerRepository.save(player2);

        Player player3 = new Player("Deivi", LocalDate.of(2003, 10, 10), 3, 13, 27, Position.SHOOTER, team3);
        playerRepository.save(player3);

        Player player4 = new Player("Aivan", LocalDate.of(2004, 10, 10), 4, 14, 28, Position.CENTER, team2);
        playerRepository.save(player4);

        Player player5 = new Player("Alesandro", LocalDate.of(2005, 10, 10), 5, 15, 29, Position.SHOOTER, team2);
        playerRepository.save(player5);
    }

    public void testPlayer(){
        System.out.println(playerRepository.findByNameStartingWith("D"));
        System.out.println(playerRepository.findByBasketsGreaterThanEqual(4));
        System.out.println(playerRepository.findByAssistsBetween(6, 13));
        System.out.println(playerRepository.findByBirthDateAfter(LocalDate.of(2003, 10, 12)));
        System.out.println(playerRepository.findByPositionEquals(Position.SHOOTER));

        playerRepository.
                findAvgOfBasketsAssistsReboundByPosition().
                forEach(player -> System.out.println(player[3]+", avg baskets: "+player[0]+", avg assists: "+player[1]+", avg rebound: "+player[2]));

        playerRepository.findAvgMinMaxOfBasketsAssistsReboundByPosition().
                forEach(aux -> System.out.println(aux[0] +
                        "\nBasket statistics -> avg: "+aux[1]+", max, "+aux[2]+", min "+aux[3]+
                        "\nAssists statistics -> avg: "+aux[4]+", max, "+aux[5]+", min "+aux[6]+
                        "\nRebound statistics -> avg: "+aux[7]+", max, "+aux[8]+", min "+aux[9]));
    }
}


