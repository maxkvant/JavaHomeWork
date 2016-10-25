package task4;
import java.util.function.Function;


public class Maybe<T> {
    private T stored;
    private Maybe() {}

    private Maybe(T stored1) {
        if (stored1 != null) {
            stored = stored1;
        } else {
            stored = null;
        }
    }

    /**
     * creates new Maybe.
     * if value is null, Maybe stores nothing,
     * otherwise Maybe stores value.
     */

    public static <T> Maybe<T> just(T value) {
        return new Maybe<T>(value);
    }

    /**
     * returns Maybe, where nothing is stored
     */

    public static <T> Maybe<T> nothing() {
        return new Maybe<T>(null);
    }

    /**
     * returns value, if value is stored,
     * otherwise throws  MaybeException.
     */

    public T get() throws MaybeException {
        if (stored != null) {
            return stored;
        } else {
            throw new MaybeException("nothing stored");
        }
    }

    /**
     * returns true if, something is stored,
     * otherwise returns false.
     */

    public boolean isPresent() {
        return stored != null;
    }

    /**
     * returns Maybe, which contains mapper(value), if value stored,
     * otherwise returns Maybe. where nothing is stored.
     */

    public <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        return stored == null ? nothing() : new Maybe<U>(mapper.apply(stored));
    }

    public static class MaybeException extends Exception {
        MaybeException(String message) {
            super(message);
        }
    }
}