package task4;

import java.util.function.Function;

/**
 *
 */

public class Maybe<T> {
    private T value;

    private Maybe() {}

    public Maybe(T value) {
        this.value = value;
    }

    /**
     * creates new Maybe.
     * if value is null, throws
     */

    public static <T> Maybe<T> just(T value) {
        if (value == null) {
            throw new IllegalArgumentException("can't create just from null");
        }
        return new Maybe<>(value);
    }

    /**
     * returns Maybe, where nothing is stored
     */

    public static <T> Maybe<T> nothing() {
        return new Maybe<>(null);
    }

    /**
     * returns value, if value is stored,
     * otherwise throws MaybeException.
     */

    public T get() throws MaybeException {
        if (value != null) {
            return value;
        } else {
            throw new MaybeException("nothing stored");
        }
    }

    /**
     * returns true if, something is stored,
     * otherwise returns false.
     */

    public boolean isPresent() {
        return value != null;
    }

    /**
     * returns Maybe, which contains mapper(value), if value stored,
     * otherwise returns Maybe. where nothing is stored.
     */

    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        return value == null ? nothing() : new Maybe<U>(mapper.apply(value));
    }

    public static class MaybeException extends Exception {
        MaybeException(String message) {
            super(message);
        }
    }
}