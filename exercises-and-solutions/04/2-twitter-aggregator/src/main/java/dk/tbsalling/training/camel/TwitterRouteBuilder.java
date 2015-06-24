package dk.tbsalling.training.camel;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

import java.util.List;

/**
 * A Camel Java DSL Router
 */
public class TwitterRouteBuilder extends org.apache.camel.builder.RouteBuilder {

    final String twitterConsumerKey = System.getenv("TWITTER_CONSUMER_KEY");
    final String twitterConsumerSecret = System.getenv("TWITTER_CONSUMER_SECRET");
    final String twitterAccessToken = System.getenv("TWITTER_CONSUMER_ACCESS_TOKEN");
    final String twitterAccessTokenSecret = System.getenv("TWITTER_CONSUMER_ACCESS_TOKEN_SECRET");

    public void configure() {
        // getContext().addInterceptStrategy(new Tracer());

        from("twitter://streaming/filter?type=event&keywords=holiday&delay=10&consumerKey=" + twitterConsumerKey + "&consumerSecret=" + twitterConsumerSecret + "&accessToken=" + twitterAccessToken + "&accessTokenSecret=" + twitterAccessTokenSecret)
        .aggregate(constant("x"), new GroupedExchangeAggregationStrategy()).completionSize(10)
        .process(exchange -> {
            Object body = exchange.getIn().getBody();
            if (body instanceof List) {
                StringBuilder listAsString = new StringBuilder();
                final int[] i = {1};
                ((List) body).forEach(listItem -> {
                    Exchange exchangeItem = (Exchange) listItem;
                    listAsString.append(i[0]++).append(": ").append(exchangeItem.getIn().getBody(String.class)).append('\n');
                });
                exchange.getIn().setBody(listAsString.append("\n----------\n").toString());
            }
        })
        .to("stream:out");
    }

}
