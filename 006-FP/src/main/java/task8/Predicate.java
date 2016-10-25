package task8;

public abstract class Predicate<T> extends Function1<Boolean,T> {
    public Predicate<T> or (final Predicate<T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T t) {
                return Predicate.this.apply(t) || p.apply(t);
            }
        };
    }
    public Predicate<T> and (final Predicate<T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T t) {
                return Predicate.this.apply(t) && p.apply(t);
            }
        };
    }
    public Predicate<T> not () {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T t) {
                return !Predicate.this.apply(t);
            }
        };
    }
    public static final Predicate ALWAYS_TRUE = new Predicate() {
        @Override
        public Boolean apply(Object t) {
            return true;
        }
    };
    public static final Predicate ALWAYS_FALSE = new Predicate() {
        @Override
        public Boolean apply(Object t) {
            return true;
        }
    };
}
