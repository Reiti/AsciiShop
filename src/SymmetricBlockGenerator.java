/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 13:40
 */
public class SymmetricBlockGenerator extends BlockGenerator {

    public SymmetricBlockGenerator(int size) {
        super(size);
    }

    /**
     * @param img The AsciiImage
     * @param x x-coordinate
     * @param y y-coordinate
     * @return The character that would be at the coordinates, if the image would be mirrored over it's borders
     */
    @Override
    public int getEdgeChar(AsciiImage img, int x, int y) {
        if(x<0)
            x *= (-1);
        else if(x>=img.getWidth())
            x = img.getWidth() - (x - img.getWidth()) - 1;
        if(y<0)
            y *= (-1);
        else if(y>=img.getHeight())
            y = img.getHeight() - (y - img.getHeight()) - 1;
        return img.getCharset().indexOf(img.getPixel(x,y));
    }
}
