package team.sensivity.teamsensivityapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import team.sensivity.teamsensivityapi.config.RsaKeyProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class TeamSensivityApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamSensivityApiApplication.class, args);
	}

}
