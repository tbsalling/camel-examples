package dk.tbsalling.training;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.TypeConverter;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.interceptor.Tracer;
import org.springframework.stereotype.Component;

/**
 * A Spring component detected and used by Spring as a Camel route
 */
@Component
public class MyRoute extends RouteBuilder {

    public void configure() {
        getContext().addInterceptStrategy(new Tracer());

        from("stream:in")
        .process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                TypeConverter converter = exchange.getContext().getTypeConverter();

                //exchange.getIn().setBody(
                converter.convertTo(Integer.class, exchange.getIn().getBody());
                //converter.convertTo(Socket.class, exchange.getIn().getBody())
                //);
            }
        })
        .to("stream:out");

        /*
        from("stream:in")
        .convertBodyTo(Pair.class)
        .to("stream:out");
        */
    }
}
