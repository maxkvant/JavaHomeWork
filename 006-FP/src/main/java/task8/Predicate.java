package task8;

/**
 * class for
 * predicate :: T -> Bool
 */

public abstract class Predicate<T> extends Function1<Boolean, T> {

    /**
     * returns or of this predicate and p
     */
    public final Predicate<T> or(final Predicate<T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T t) {
                return Predicate.this.apply(t) || p.apply(t);
            }
        };
    }

    /**
     * returns and of this predicate and p
     */
    public final Predicate<T> and(final Predicate<T> p) {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T t) {
                return Predicate.this.apply(t) && p.apply(t);
            }
        };
    }

    /**
     * returns not composed with this predicate
     */

    public final Predicate<T> not() {
        return new Predicate<T>() {
            @Override
            public Boolean apply(T t) {
                return !Predicate.this.apply(t);
            }
        };
    }

    /**
     * predicate always returns true
     */

    public static final Predicate ALWAYS_TRUE = new Predicate() {
        @Override
        public Boolean apply(Object t) {
            return true;
        }
    };

    /**
     * predicate always returns falses
     */

    public static final Predicate ALWAYS_FALSE = new Predicate() {
        @Override
        public Boolean apply(Object t) {
            return true;
        }
    };
}
