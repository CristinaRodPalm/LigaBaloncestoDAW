package palmer.cristina.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import palmer.cristina.domain.Player;
import palmer.cristina.domain.Position;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cristina on 10/10/2016.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // a. Buscar jugadores por nombre, de manera que no sea necesario introducir el nombre completo.
    List<Player> findByNameStartingWith(String name);

    // b. Buscar jugadores que hayan conseguido un número mayor o igual a un número de canastas especificado como parámetro.
    List<Player> findByBasketsGreaterThanEqual  (int baskets);

    //c. Buscar jugadores que hayan efectuado un número de asistencias dentro de un rango especificado como parámetro.
    List<Player> findByAssistsBetween(int min, int max);

    // d. Buscar jugadores que pertenezcan a una posición específica, por ejemplo: base.
    List<Player> findByPositionEquals(Position position);

    // e. Buscar jugadores que hayan nacido en una fecha anterior a una fecha especificada como parámetro.
    List<Player> findByBirthDateAfter(LocalDate birth);

    // f. Agrupar los jugadores por la posición del campo y devolver para cada grupo la siguiente información: la media de canastas, asistencias y rebotes.
    @Query("select avg(p.baskets), avg(p.assists), avg(p.rebound), p.position from Player p group by p.position")
    List<Object[]> findAvgOfBasketsAssistsReboundByPosition ();

    // g. Lo mismo que el punto anterior pero devolviendo la media, el máximo y el mínimo de canastas, asistencias y rebotes.
    @Query("select p.position, avg(p.baskets), max(p.baskets), min(p.baskets), avg(p.assists), max(p.assists), min(p.assists), avg(p.rebound), max(p.rebound), min(p.rebound) from Player p group by p.position")
    List<Object[]> findAvgMinMaxOfBasketsAssistsReboundByPosition ();


    //    --> Práctica 2

    // 1. Devolver todos los jugadores ordenados por número de canastas.
    @Query("select p from Player p order by p.baskets asc")
    List<Player> orderByBaskets();

    //2. Devolver todos los jugadores que han conseguido un número de canastas igual o superior a un parámetro especificado en la URL.
    //List<Player> findByBasketsGreaterThanEqual  (int baskets);

    // 3. Devolver todos los jugadores que han conseguido un número de canastas en un rango determinado (mínimo y máximo).
    List<Player> findByBasketsBetween(int min, int max);

    // 4. Devolver los jugadores agrupados por posición mediante un Map.
    @Query("select p.position, min(p.baskets), max(p.baskets), avg(p.baskets) from Player p group by p.position")
    List<Object[]> groupByPosition();

    // 5. Devolver los jugadores agrupados por posición mediante un Map. Para cada posición mostrar las siguientes estadísticas:
        // mínimo, máximo y media del número de canastas (podéis experimentar con otros valores como, por ejemplo, asistencias).
    @Query("select p from Player p order by p.baskets")
    List<Player> playersByPositionBaskets();

}