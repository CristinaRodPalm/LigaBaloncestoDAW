package palmer.cristina.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import palmer.cristina.domain.Player;
import palmer.cristina.domain.Position;
import palmer.cristina.domain.Team;

import java.util.List;

/**
 * Created by Cristina on 13/10/2016.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    // a. Consulta los equipos existentes en una localidad determinada.
    List<Team> findTeamByCity(String city);

    // b. Devuelve todos los jugadores de un equipo, a partir del nombre completo del equipo.
    @Query("select p from Player p, Team t where t.id = p.team and t.name = :teamName")
    List<Player> findPlayersByTeamName(@Param("teamName") String name);

    // c. Devuelve todos los jugadores de un equipo, que además jueguen en la misma posición (parámetro adicional de la consulta), por ejemplo, alero.
    @Query("select p from Player p, Team t where t.id = p.team and t.name = :teamName and p.position = :playerPosition")
    List<Player> findPlayersByTeamNameAndPosition(@Param("teamName") String name, @Param("playerPosition") Position position);

    // d. Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.
    @Query("select p from Player p, Team t where t.id = p.team and t.name = :teamName order by baskets desc")
    List<Player> findMoreBasketsByTeam(@Param("teamName") String name);
}
