package dk.tbsalling.training.camel.firstapp;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class FirstCamelApp {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();
        main.addRouteBuilder(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer://timer1?period=1000")
                .log("Camel's heart beat");
            }
        });
        main.run(args);
    }
}
