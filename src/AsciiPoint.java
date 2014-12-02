/**
 * User: Michael Reitgruber
 * Date: 18.11.2014
 * Time: 13:42
 */
public class AsciiPoint {
    private final int x;
    private final int y;

    public AsciiPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "("+x+","+y+")";
    }
}
