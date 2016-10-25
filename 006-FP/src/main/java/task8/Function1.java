package task8;

public abstract class Function1<Z, Y> {
    public Z apply(Y y) {
        throw new UnsupportedOperationException();
    }

    public <X> Function1<Z, X> compose(final Function1<Y, X> g) {
        return new Function1<Z, X>() {
            @Override
            public Z apply(X x) {
                return Function1.this.apply(g.apply(x));
            }
        };
    }
}
