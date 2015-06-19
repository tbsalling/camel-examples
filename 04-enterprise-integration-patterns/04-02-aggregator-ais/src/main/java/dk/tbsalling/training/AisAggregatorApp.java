package dk.tbsalling.training;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A Spring Boot Application
 */
@SpringBootApplication
public class AisAggregatorApp implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(AisAggregatorApp.class);

    @Autowired
    private ProducerTemplate producerTemplate;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AisAggregatorApp.class);
        app.setWebEnvironment(false);
        app.run();
    }

    @Override
    public void run(String... strings) throws Exception {
        Path path = Paths.get("data/data.ais");
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            producerTemplate.sendBody("direct:ais", line);

        }

        LOG.info("Press Ctrl-C to stop application...");
        Thread.currentThread().join();
    }
}

