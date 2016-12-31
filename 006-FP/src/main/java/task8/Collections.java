package task8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * class contains static methods for work with Iterable
 */

public class Collections {
    /**
     * returns new iterable, which consists of results making (apply f) to elements iterable
     */

    public static <Z, Y> Iterable<Z> map(Function1<? extends Z, ? super Y> f, Iterable<Y> iterable) {
        List<Z> res = new ArrayList<>();
        Iterator<Y> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            res.add(f.apply(iterator.next()));
        }
        return res;
    }

    /**
     * returns new iterable, which consists of elements, where predicate(element) = true
     */

    public static <Y> Iterable<Y> filter(Predicate<? super Y> p, Iterable<Y> iterable) {
        List<Y> res = new ArrayList<>();
        Iterator<Y> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Y cur = iterator.next();
            if (p.apply(cur)) {
                res.add(cur);
            }
        }
        return res;
    }

    /**
     * returned iterable takes elements while predicate(element) == true
     */

    public static <Y> Iterable<Y> takeWhile(Predicate<? super Y> p, Iterable<Y> iterable) {
        List<Y> res = new ArrayList<>();
        Iterator<Y> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Y cur = iterator.next();
            if (p.apply(cur)) {
                res.add(cur);
            } else {
                break;
            }
        }
        return res;
    }

    /**
     * takeWhile(p.not(), iterable)
     */

    public static <Y> Iterable<Y> takeUnless(Predicate<? super Y> p, Iterable<Y> iterable) {
        return takeWhile(p.not(), iterable);
    }

    /**
     * works like foldl in haskell
     */

    public static <Z,Y> Z foldl(Function2<Z, Z, Y> f, Z y0, Iterable<Y> iterable) {
        Iterator<Y> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            y0 = f.apply(y0, iterator.next());
        }
        return y0;
    }

    /**
     * works like foldr in haskell
     */

    public static <Z,Y> Z foldr(Function2<? extends Z, ? super Y, ? super Z> f, Z y0, Iterable<Y> a) {
        List<Y> arr = StreamSupport.stream(a.spliterator(), false)
                .collect(Collectors.toList());

        java.util.Collections.reverse(arr);
        for (Y y : arr) {
            y0 = f.apply(y, y0);
        }
        return y0;
    }
}
