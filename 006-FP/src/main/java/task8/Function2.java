package task8;

public class Function2<Z, X, Y> {
    public Z apply(X x, Y y) {
        throw new UnsupportedOperationException();
    }
    public Function1<Z, Y> bind1(final X x) {
        return new Function1<Z, Y>() {
            @Override
            public Z apply(Y y) {
                return Function2.this.apply(x, y);
            }
        };
    }
    public Function1<Z, X> bind2(final Y y) {
        return new Function1<Z, X>() {
            @Override
            public Z apply(X x) {
                return Function2.this.apply(x, y);
            }
        };
    }
    public Function1<Function1<Z,X>, Y> curry() {
        return new Function1<Function1<Z,X>, Y>() {
            @Override
            public Function1<Z, X> apply(Y y) {
                return Function2.this.bind2(y);
            }
        };
    }
}
