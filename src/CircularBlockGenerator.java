/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 13:39
 */
public class CircularBlockGenerator extends BlockGenerator {

    public CircularBlockGenerator(int size) {
        super(size);
    }


    /**
     *
     * @param img The image
     * @param x x-coordinate
     * @param y y-coordinate
     * @return The brightness value of the character that would be at this position if the image would be repeated beyond it's borders
     */
    @Override
    public int getEdgeChar(AsciiImage img, int x, int y) {
        while(x>=img.getHeight())
            x -= img.getWidth();
        while(x<0)
            x += img.getWidth();
        while(y>=img.getHeight())
            y -= img.getHeight();
        while(y<0)
            y += img.getHeight();
        return img.getCharset().indexOf(img.getPixel(x,y));
    }

}
