package task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Collections<Y>  {
    public <Z> Iterable<Z> map(Function1<Z, Y> f, Iterable<Y> a) {
        ArrayList<Z> res = new ArrayList<Z>();
        Iterator<Y> iterator = a.iterator();
        while (iterator.hasNext()) {
            res.add(f.apply(iterator.next()));
        }
        return res;
    }
    public Iterable<Y> filter(Predicate<Y> p, Iterable<Y> a) {
        ArrayList<Y> res = new ArrayList<Y>();
        Iterator<Y> iterator = a.iterator();
        while (iterator.hasNext()) {
            Y cur = iterator.next();
            if (p.apply(cur)) {
                res.add(cur);
            }
        }
        return res;
    }
    public Iterable<Y> takeWhile(Predicate<Y> p, Iterable<Y> a) {
        ArrayList<Y> res = new ArrayList<Y>();
        Iterator<Y> iterator = a.iterator();
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
    public Iterable<Y> takeUnless(Predicate<Y> p, Iterable<Y> a) {
        return takeWhile(p.not(), a);
    }
    public <Z> Z foldl(Function2<Z, Z, Y> f, Z y0, Iterable<Y> a) {
        Iterator<Y> iterator = a.iterator();
        while (iterator.hasNext()) {
            y0 = f.apply(y0, iterator.next());
        }
        return y0;
    }
    public <Z> Z foldr(Function2<Z, Y, Z> f, Z y0, Iterable<Y> a) {
        ArrayList<Y> arr = (ArrayList<Y>)filter(Predicate.ALWAYS_TRUE, a);
        java.util.Collections.reverse(arr);
        for (Y y : arr) {
            y0 = f.apply(y, y0);
        }
        return y0;
    }
}
