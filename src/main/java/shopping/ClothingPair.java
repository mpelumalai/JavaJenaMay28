package shopping;

public class ClothingPair<E extends Colored & Sized/* BADBADBAD!!!, String*/> extends Pair<E> {
//    java.lang.String x = "Hello";

    public ClothingPair(E left, E right) {
        super(left, right);
    }

    public boolean matches() {
        return left.getColor().equals(right.getColor())
                && left.getSize() == right.getSize();
    }
}
