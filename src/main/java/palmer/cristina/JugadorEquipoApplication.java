package palmer.cristina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import palmer.cristina.service.PlayerService;

@SpringBootApplication
public class JugadorEquipoApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(JugadorEquipoApplication.class, args);

        PlayerService playerService = context.getBean(PlayerService.class);

        playerService.crearJugadores();
        playerService.test();
    }
}
