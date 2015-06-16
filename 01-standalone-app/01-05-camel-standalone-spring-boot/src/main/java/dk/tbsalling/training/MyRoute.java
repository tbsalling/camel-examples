package dk.tbsalling.training;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    public void configure() {
        from("timer://timer1?period=1000")
        .log("Camel's heart beat");
    }

}
