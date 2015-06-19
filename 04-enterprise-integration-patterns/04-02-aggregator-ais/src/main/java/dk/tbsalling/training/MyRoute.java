package dk.tbsalling.training;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.interceptor.DefaultTraceFormatter;
import org.apache.camel.processor.interceptor.Tracer;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    public static final String AIS_SENTENCE_COUNTER = "AIS.SENTENCE_COUNTER";

    public void configure() {

        Tracer tracer = new Tracer();
        getContext().addInterceptStrategy(tracer);

        DefaultTraceFormatter traceFormatter = tracer.getDefaultTraceFormatter();
        traceFormatter.setShowProperties(true);

        from("direct:ais")
        .aggregate(
                constant("-"),
                (oldExchange, newExchange) -> {
                    if (oldExchange == null) return newExchange;

                    String oldAsString = oldExchange.getIn().getBody(String.class);
                    String newAsString = newExchange.getIn().getBody(String.class);

                    oldExchange.getIn().setBody(oldAsString + '\n' + newAsString);

                    Integer cnt = (Integer) oldExchange.getProperty(AIS_SENTENCE_COUNTER);
                    cnt = cnt == null ? 1 : cnt + 1;
                    oldExchange.setProperty(AIS_SENTENCE_COUNTER, cnt);

                    return oldExchange;
                })
        .completionPredicate(exchange -> {
            Integer cnt = (Integer) exchange.getProperty(AIS_SENTENCE_COUNTER);
            cnt = cnt == null ? 1 : cnt + 1;

            String body = exchange.getIn().getBody(String.class);
            String[] split = body.split("\n");
            String lastLine = split[split.length - 1];
            Integer expectedMsgs = Integer.valueOf(lastLine.split(",")[1]);

            return cnt >= expectedMsgs;
        })
        .completionTimeout(1000)
        .process(exchange1 -> exchange1.getIn().setBody(exchange1.getIn().getBody(String.class) + "\n---"))
        .to("stream:out");
    }
}