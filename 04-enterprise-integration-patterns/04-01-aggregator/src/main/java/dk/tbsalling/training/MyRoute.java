package dk.tbsalling.training;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    public void configure() {

        from("stream:in")
        .aggregate(constant("1"), new AggregationStrategy() {
        //.aggregate(body(String.class), new AggregationStrategy() {
            @Override
            public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
                if (oldExchange == null) return newExchange;

                String oldAsString = oldExchange.getIn().getBody(String.class);
                String newAsString = newExchange.getIn().getBody(String.class);

                oldExchange.getIn().setBody(oldAsString + newAsString);

                return oldExchange;
            }
        })
        .completionSize(3) // .completionTimeout(10000)
        //.completionPredicate(exchange -> exchange.getIn().getBody(String.class).equalsIgnoreCase("ZZ"))
        .to("stream:err");

        // ---

        from("direct:A")
        .aggregate(constant("1"), (oldExchange, newExchange) -> {
            if (oldExchange == null) return newExchange;

            String oldAsString = oldExchange.getIn().getBody(String.class);
            String newAsString = newExchange.getIn().getBody(String.class);

            oldExchange.getIn().setBody(oldAsString + newAsString);

            return oldExchange;
        })
        .completionSize(3)
        .to("stream:err");

    }
}