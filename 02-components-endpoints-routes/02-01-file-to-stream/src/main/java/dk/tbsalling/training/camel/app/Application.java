package dk.tbsalling.training.camel.app;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import static java.lang.Thread.sleep;

public class Application {

    public static final RouteBuilder ROUTE_BUILDER_1 = new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("file://src/data?noop=true")
            .to("stream:out");
        }
    };

    public static final RouteBuilder ROUTE_BUILDER_2 = new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("file://src/data?noop=true")
            .split().tokenize("\n")
            //.filter().simple("${body} regex '^\\!..VDM,1,1,.*$'")
            .filter(body().regex("^\\!..VDM,1,1,.*$"))
            .to("stream:out");
        }
    };

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();

        main.addRouteBuilder(ROUTE_BUILDER_2);

        main.run(args);
    }

}
