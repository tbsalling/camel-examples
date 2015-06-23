package dk.tbsalling.training.camel;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConverter;
import twitter4j.Status;

@Converter
public class MyTweetConverter {

    static { System.out.println("LOADED");}
    { System.out.println("CONSTRUCTED");}

    @Converter
    public static MyTweet toMyTweet(Status twitterStatus, Exchange exchange) {
        String text = twitterStatus.getText();
        String source = twitterStatus.getSource();

        TypeConverter typeConverter = exchange.getContext().getTypeConverter();
        String tweetAsString = typeConverter.convertTo(String.class, twitterStatus);
        int hashCode = tweetAsString.hashCode();

        return new MyTweet(text, source, hashCode);
    }
}