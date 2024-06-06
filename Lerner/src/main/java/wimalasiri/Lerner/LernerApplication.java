package wimalasiri.Lerner;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//40 min seen
import org.springframework.context.annotation.Bean;

import wimalasiri.Lerner.run.Location;
import wimalasiri.Lerner.run.Run;
@SpringBootApplication
public class LernerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LernerApplication.class, args);

		// var welcome = new welcome();
		// System.out.println(welcome.getWelcome());
		
	}
	// Command line runner is excute after the spring boot apllication running started
	@Bean
	CommandLineRunner runner(){ 
		Logger logger = LoggerFactory.getLogger(LernerApplication.class);
		return args -> {
			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.INDOOR);
			logger.info("Run" + run);
		};
	}

}
