package dk.tbsalling.training.camel;

public class TranformerBean {

    public static String map(String body) {
        return "--> " + body;
    }

}
