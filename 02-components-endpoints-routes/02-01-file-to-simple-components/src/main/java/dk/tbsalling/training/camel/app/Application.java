package dk.tbsalling.training.camel.app;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import static java.lang.Thread.sleep;

public class Application {

    public static final RouteBuilder ROUTE_FILE_TO_FILE = new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("file://src/data/in?noop=true")
            .to("file://src/data/out/");
        }
    };

    public static final RouteBuilder ROUTE_FILE_TO_STREAM = new RouteBuilder() {
        @Override
        public void configure() throws Exception {
            from("file://src/data?noop=true")
            .to("stream:out");
        }
    };

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();

        main.addRouteBuilder(ROUTE_FILE_TO_FILE);

        main.run(args);
    }

}
