package dk.tbsalling.training.camel;

import net.jcip.annotations.Immutable;

@Immutable
public class Pair {
    private final int a;
    private final int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pair{");
        sb.append("a=").append(a);
        sb.append(", b=").append(b);
        sb.append('}');
        return sb.toString();
    }
}
