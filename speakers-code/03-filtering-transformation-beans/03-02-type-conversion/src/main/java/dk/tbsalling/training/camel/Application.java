package dk.tbsalling.training.camel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot Application
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebEnvironment(false);
        app.run();
    }

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Press Ctrl-C to stop application...");
        Thread.currentThread().join();
    }
}

