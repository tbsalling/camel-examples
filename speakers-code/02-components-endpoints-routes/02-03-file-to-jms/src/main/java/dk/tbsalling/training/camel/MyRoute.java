package dk.tbsalling.training.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    public void configure() {
        from("timer://timer1?period=1000")
        .process(exchange -> {
            exchange.getIn().setBody(exchange.getProperty("CamelTimerFiredTime"));
        })
        //.log(exchangeProperty("CamelTimerFiredTime").convertToString().toString())
        .to("activemq:topic:TEST.TOPIC?jmsMessageType=Object");
    }

}
