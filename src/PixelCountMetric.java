/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:29
 */
public class PixelCountMetric implements Metric<AsciiImage> {
    public int distance(AsciiImage o1, AsciiImage o2) {
        return Math.abs(o1.getWidth()*o1.getHeight() - o2.getWidth()*o2.getHeight());
    }
}
