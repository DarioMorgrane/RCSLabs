package testRCS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Main Spring Boot Application class
 *
 * @author  Daniil Mikhailenko
 */
@SpringBootApplication
@PropertySource("classpath:DataSource.properties")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}