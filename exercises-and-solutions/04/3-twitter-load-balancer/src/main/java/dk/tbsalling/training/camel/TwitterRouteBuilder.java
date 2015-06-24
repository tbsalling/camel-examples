package dk.tbsalling.training.camel;

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

        from("twitter://streaming/filter?type=event&keywords=camel&delay=10&consumerKey=" + twitterConsumerKey + "&consumerSecret=" + twitterConsumerSecret + "&accessToken=" + twitterAccessToken + "&accessTokenSecret=" + twitterAccessTokenSecret)
        .convertBodyTo(String.class)
        .transform(body().append("\n"))
        .loadBalance()
        .roundRobin()
        .to(
                "file://data?fileName=tweets-file-1.txt&fileExist=append",
                "file://data?fileName=tweets-file-2.txt&fileExist=append",
                "file://data?fileName=tweets-file-3.txt&fileExist=append",
                "file://data?fileName=tweets-file-4.txt&fileExist=append"
        );
    }

}
