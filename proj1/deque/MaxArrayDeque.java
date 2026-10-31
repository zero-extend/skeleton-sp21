package deque;

import java.util.Comparator;
/**
 * @author niannian
 * @date 2026/10/31 12:12
 * @description
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> x;

    /** super() : call the construction of father , no argument. */
    /** super(argument) : call the construction of father , has argument. */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        x = c;
    }

    public T max() {
        T max = max(x);
        return max;
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }

        int size = this.size();
        T max = get(0);
        for (T i : this) {
            if (c.compare(i, max) > 0) {
                max = i;
            }
        }
        return max;

    }

}
