/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 13:40
 */
public class ReplicateBlockGenerator extends BlockGenerator {

    public ReplicateBlockGenerator(int size) {
        super(size);
    }

    /**
     * Returns the character at the edge of the image nearest to the given coordinates
     * @param img The image
     * @param x x-coordinate
     * @param y y-coordinate
     * @return The replicated edge character
     */
    public int getEdgeChar(AsciiImage img, int x, int y) {
        while(x>=img.getWidth())
            x--;
        while(x<0)
            x++;
        while(y>=img.getHeight())
            y--;
        while(y<0)
            y++;
        return img.getCharset().indexOf(img.getPixel(x,y));
    }
}
