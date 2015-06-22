package dk.tbsalling.training;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    public void configure() {
        // getContext().addInterceptStrategy(new Tracer());

        // Transformation; not conversion


        from("stream:in")
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                String body = exchange.getIn().getBody(String.class);
                body = "--> " + body;
                exchange.getIn().setBody(body);
            }
        })
        .to("stream:out");

        /*
        from("stream:in")
        .bean(new TranformerBean())
        .to("stream:out");
        */

        /*
        from("stream:in")
        .transform(simple("=-> ${body}"))
        //.transform(constant("==> ").append(body(String.class)))
        .to("stream:out");
        */
    }
}