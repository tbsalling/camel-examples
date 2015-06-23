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
        .to("bean:heartBeatBean");

        //from("timer://timer1?period=1000")
        //.to("bean:heartBeatBean?method=tock");
        //.to("bean:heartBeatBean?method=tock(${body}, true)");
    }

}
