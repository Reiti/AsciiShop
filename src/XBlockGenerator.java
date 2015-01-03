import java.util.ArrayList;
import java.util.List;

/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 13:39
 */
public class XBlockGenerator extends BlockGenerator {

    public XBlockGenerator(int size) {
        super(size);
    }

    /**
     *
     * @param img The image
     * @param x x-coordinate
     * @param y y-coordinate
     * @return The brightest color in the charset
     */
    @Override
    public int getEdgeChar(AsciiImage img, int x, int y) {
        return img.getCharset().length()-1;
    }
}
