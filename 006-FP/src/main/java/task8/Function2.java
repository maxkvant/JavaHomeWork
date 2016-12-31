package task8;

/**
 * class for
 * function :: X -> Y -> Z
 */

public abstract class Function2<Z, X, Y> {
    /**
     * applies this function
     */
    public abstract Z apply(X x, Y y);

    /**
     * returns this function with fixed first argument
     * \y -> f(x, y)
     */
    public final Function1<Z, Y> bind1(final X x) {
        return new Function1<Z, Y>() {
            @Override
            public Z apply(Y y) {
                return Function2.this.apply(x, y);
            }
        };
    }

    /**
     * returns this function with fixed second argument
     * \x -> f(x, y)
     */
    public final Function1<Z, X> bind2(final Y y) {
        return new Function1<Z, X>() {
            @Override
            public Z apply(X x) {
                return Function2.this.apply(x, y);
            }
        };
    }

    /**
     * converts this function to function1
     */
    public final Function1<Function1<Z,X>, Y> curry() {
        return new Function1<Function1<Z,X>, Y>() {
            @Override
            public Function1<Z, X> apply(Y y) {
                return Function2.this.bind2(y);
            }
        };
    }
}
