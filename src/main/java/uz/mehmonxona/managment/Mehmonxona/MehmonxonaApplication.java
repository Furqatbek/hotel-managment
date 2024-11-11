package uz.mehmonxona.managment.Mehmonxona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MehmonxonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MehmonxonaApplication.class, args);
	}

}
