/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:34
 */
public class UniqueCharsMetric implements Metric<AsciiImage> {
    public int distance(AsciiImage o1, AsciiImage o2) {
        return Math.abs(o1.getUniqueChars() - o2.getUniqueChars());
    }
}
