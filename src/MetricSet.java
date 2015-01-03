import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:35
 */
public class MetricSet<E> extends LinkedHashSet<E> {

    public MetricSet() {
        super();
    }

    public MetricSet(Collection<? extends E> c) {
        this.addAll(c);
    }

    /**
     * Returns a MetricSet of Objects with the minimum semi-metric distance to a given object with a given metric
     * @param e The object from which to calculate the distance
     * @param m The metric to calculate the distance
     * @return A set of objects with minimum distance
     */
    public MetricSet<E> search(E e, Metric<? super E> m) {
        MetricSet<E> results = new MetricSet<E>();
        Iterator<E> it = iterator();
        int min = Integer.MAX_VALUE;

        while(it.hasNext()) {
            E curr = it.next();
            int dist = m.distance(e,curr);
            if(dist < min) {
                min = dist;
                results = new MetricSet<E>();
                results.add(curr);
            }
            else if(dist == min) {
                results.add(curr);
            }
        }
        return results;
    }

}
