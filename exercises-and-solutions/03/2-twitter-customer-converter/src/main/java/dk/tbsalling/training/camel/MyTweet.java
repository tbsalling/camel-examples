package dk.tbsalling.training.camel;

public class MyTweet {
    public MyTweet(String text, String source, int hash) {
        this.text = text;
        this.source = source;
        this.hash = hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyTweet{");
        sb.append("text='").append(text).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", hash=").append(hash);
        sb.append('}');
        return sb.toString();
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public int getHash() {
        return hash;
    }

    private final String text;
    private final String source;
    private final int hash;
}
