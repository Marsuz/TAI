package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"core"})
@ComponentScan({
        "core",
        "web",
        "persistence",
        "services",
        "rest"
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}