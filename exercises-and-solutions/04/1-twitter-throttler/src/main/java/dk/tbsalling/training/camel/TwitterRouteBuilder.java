package dk.tbsalling.training.camel;

import java.time.Instant;
import java.time.LocalTime;

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
        .throttle(1).timePeriodMillis(1000).asyncDelayed()
        .transform(simple("${date:now:HH:mm:ss.SSS} --> ${body}"))
        .to("stream:out");
    }

}
