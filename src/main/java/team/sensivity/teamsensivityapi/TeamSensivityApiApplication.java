package team.sensivity.teamsensivityapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import team.sensivity.teamsensivityapi.timers.EveryMinute;

import java.util.Timer;

@SpringBootApplication

public class TeamSensivityApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamSensivityApiApplication.class, args);
		new Timer().schedule(new EveryMinute(), 0, 1000 * 60);
	}

}
