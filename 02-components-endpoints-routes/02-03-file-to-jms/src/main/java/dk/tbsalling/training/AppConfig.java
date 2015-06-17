package dk.tbsalling.training;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

@EnableAsync
@Configuration
public class AppConfig {

    @Autowired
    CamelContext camelContext;

    @Bean
    public CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                context.addComponent("activemq", activeMQComponent("vm://localhost?broker.persistent=false"));
            }
        };
    }

}

