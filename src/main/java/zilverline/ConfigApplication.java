package zilverline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

}
