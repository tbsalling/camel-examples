package dk.tbsalling.training.camel;

import org.apache.camel.main.Main;

/**
 * A Spring Boot Application
 */
@SpringBootApplication
public class MainApp implements CommandLineRunner {

    private final static Logger LOG = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Press Ctrl-C to stop application...");
        Thread.currentThread().join();
    }
}
