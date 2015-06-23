package dk.tbsalling.training.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.MediaType;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    public void configure() {

        // 1. SIMPLE

        /*
        from("stream:in")
        .to("stream:out")
        .to("stream:err");
        */

        // 2. Use direct:

        from("stream:in")
        .to("direct:A");

        /*
        from("direct:A")
        .to("direct:step1")
        .to("direct:step2");
        */

        // 3. Use multicast
        /*
        from("direct:A").multicast().parallelProcessing()
        .to("direct:step1")
        .to("direct:step2");

        from("direct:step1")
        .transform(simple("STEP 1: ${body}"))
        .to("stream:out");

        from("direct:step2")
        .transform(simple("STEP 2: ${body}"))
        .to("stream:err");
        */

        // 4. Filtering
        /*
        from("direct:A")
        .filter(simple("${body} != 'CAMEL'"))
        .to("stream:err");
        */

        // 4. Content-based routing
        /*
        from("direct:A")
        .choice()
            .when(simple("${body} != 'CAMEL'"))
                .to("stream:err")
            .otherwise()
                .to("stream:out")
        .end();
        */

        from("direct:A")
        .choice()
            .when(simple("${body} != 'CAMEL'"))
                .to("stream:err")
            .otherwise()
                .to("stream:out").stop()
        .end()
        .log(body().convertToString().toString());

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