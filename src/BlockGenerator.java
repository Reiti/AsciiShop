/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 13:39
 */
public abstract class BlockGenerator {

    protected int size;
    public BlockGenerator(int size) {
        this.size = size;
    }


    public int[] getBlock(AsciiImage img, int x, int y) {
        int[] brightness = new int[size*size];
        //Increment x and y coordinates so we get the bottom right corner of the  box
        x += (size / 2);
        y += (size / 2);
        //Starting from bottom right corner we decrement x and y until we reach the top left corner
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //if we reach beyond the border during decrementing we add a character depending on the type of border treatment
                if (x - i < 0 || y - j < 0 || x - i >= img.getWidth() || y - j >= img.getHeight()) {
                    brightness[i * size + j] = getEdgeChar(img,x-i,y-j);
                }
                //else we add the pixels at the decremented position
                else {
                    brightness[i * size + j] = img.getCharset().indexOf(img.getPixel(x - i, y - j));
                }

            }
        }
        return brightness;
    }

    public abstract int getEdgeChar(AsciiImage img, int x, int y);
}
