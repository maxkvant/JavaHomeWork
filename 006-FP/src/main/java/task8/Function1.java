package task8;

/**
 * class for
 * function :: Y -> Z
 */

public abstract class Function1<Z, Y> {
    /**
     * applies this function
     */
    public abstract Z apply(Y y);

    /**
     * composes this function with g
     */

    public final <X> Function1<Z, X> compose(final Function1<? extends Y, ? super X> g) {
        return new Function1<Z, X>() {
            @Override
            public Z apply(X x) {
                return Function1.this.apply(g.apply(x));
            }
        };
    }

    /**
     * composes this function with g
     */

    public final <X, W> Function2<Z, X, W> compose(final Function2<? extends Y, ? super X, ? super W> g) {
        return new Function2<Z, X, W>() {
            @Override
            public Z apply(X x, W w) {
                return Function1.this.apply(g.apply(x, w));
            }
        };
    }
}
