package zilverline;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class CustomConfiguration {

    @Bean
    public UserConfig userConfig() throws IOException {
        return new UserConfig(new ObjectMapper(new YAMLFactory())
                .readValue(new ClassPathResource("/users.yml").getInputStream(), new TypeReference<>() {
        }));
    }

}
