package dk.tbsalling.training;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.MediaType;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    // 1. Make sure that RestServerApp is running
    // 2. Type this into the stdin: { "mmsi": 219999999, "name": "TEST 2", "latitude": 55.71, "longitude": 10.01, "speed": 0, "course": 0 }
    // 3. Use postman or curl to check GET http://localhost:8080/vessels

    public void configure() {

        from("stream:in")
        .to("log:dk.tbsalling.training.camel")
        .process(exchange -> exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .to("restlet:http://localhost:8080/vessel?restletMethod=POST");

        // --- Playing with CBR and (un)marshalling

        /*
        from("stream:in")
        .to("log:dk.tbsalling.training.camel")
        .unmarshal().json(JsonLibrary.Jackson)
        .to("log:dk.tbsalling.training.camel")
            .choice()
                .when().simple("${body[mmsi]} < 100000000")
                    .log(LoggingLevel.WARN, "*** ${body}")
                    .to("stream:err")
                .otherwise()
                    .to("stream:out")
            .end()
        .process(exchange -> exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON))
        .marshal().json(JsonLibrary.Jackson)
        .to("restlet:http://localhost:8080/vessel?restletMethod=POST");
        */
    }
}