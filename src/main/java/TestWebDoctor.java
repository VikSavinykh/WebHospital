import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "domain", "service", "config"})
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableConfigurationProperties(ThymeleafProperties.class)
public class TestWebDoctor extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestWebDoctor.class, args);
    }

}
