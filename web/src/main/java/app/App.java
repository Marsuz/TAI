package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"model", "wrappers"})
@EnableJpaRepositories(basePackages = {"repositories"})
@ComponentScan({
        "services", "repositories", "controllers"
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}