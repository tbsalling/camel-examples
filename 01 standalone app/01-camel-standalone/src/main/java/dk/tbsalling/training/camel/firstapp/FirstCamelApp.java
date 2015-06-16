package dk.tbsalling.training.camel.firstapp;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import static java.lang.Thread.sleep;

/**
 * Created by tbsalling on 02/06/15.
 */
public class FirstCamelApp {

    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer://timer1?period=1000")
                .log("Camel's heart beat");
            }
        });

        context.start();
        sleep(10000);
        context.stop();
    }
}
