package dk.tbsalling.training.camel;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConverter;

@Converter
public class MyCustomConverter {

    @Converter
    public static Pair toPair(String string, Exchange exchange) {
        String[] split = string.split(",");

        TypeConverter typeConverter = exchange.getContext().getTypeConverter();
        int a = typeConverter.convertTo(Integer.class, split[0]);
        int b = typeConverter.convertTo(Integer.class, split[1]);

        return new Pair (a, b);
    }
}