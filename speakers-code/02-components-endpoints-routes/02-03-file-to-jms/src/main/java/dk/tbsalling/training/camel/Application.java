package dk.tbsalling.training.camel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot Application
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private JmsConsumer jmsConsumer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Press Ctrl-C to stop application...");
        jmsConsumer.run();
        Thread.currentThread().join();
    }
}

