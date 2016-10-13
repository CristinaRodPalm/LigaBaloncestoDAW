package palmer.cristina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
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

    // c. Devuelve todos los jugadores de un equipo, que además jueguen en la misma posición (parámetro adicional de la consulta), por ejemplo, alero.

    // d. Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.


}
