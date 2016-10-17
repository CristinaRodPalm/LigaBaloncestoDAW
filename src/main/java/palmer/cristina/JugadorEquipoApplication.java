package palmer.cristina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import palmer.cristina.service.PlayerService;
import palmer.cristina.service.TeamService;

@SpringBootApplication
public class JugadorEquipoApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(JugadorEquipoApplication.class, args);

        TeamService teamService = context.getBean(TeamService.class);
        PlayerService playerService = context.getBean(PlayerService.class);

        teamService.createTeam();

        playerService.crearJugadores();
        playerService.testPlayer();

        teamService.testTeam();


    }
}