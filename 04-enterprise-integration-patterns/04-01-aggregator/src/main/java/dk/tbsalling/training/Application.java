package dk.tbsalling.training;

import org.apache.camel.ProducerTemplate;
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
    ProducerTemplate producerTemplate;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebEnvironment(false);
        app.run();
    }

    @Override
    public void run(String... strings) throws Exception {
        producerTemplate.sendBody("direct:A", "A");
        producerTemplate.sendBody("direct:A", "B");
        producerTemplate.sendBody("direct:A", "C");
        producerTemplate.sendBody("direct:A", "D");
        producerTemplate.sendBody("direct:A", "E");
        producerTemplate.sendBody("direct:A", "F");
        producerTemplate.sendBody("direct:A", "G");

        LOG.info("Press Ctrl-C to stop application...");
        Thread.currentThread().join();
    }
}

