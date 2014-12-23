import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * User: Michael Reitgruber
 * Date: 23.12.2014
 * Time: 19:35
 */
public class MedianOperation implements Operation {

    //Inline the charset comparator since the test environment won't let me upload
    private class CharsetComparator implements Comparator<Character> {
        private String charset;

        public CharsetComparator(String charset) {
            this.charset = charset;
        }

        /**
         * Compares to characters within their respective charset
         * @param o1 first character
         * @param o2 second character
         * @return -1 if first is darker than second, 1 if first is brighter, 0 if they are equally bright
         */
        public int compare(Character o1, Character o2) {
            if(charset.indexOf(o1) < charset.indexOf(o2))
                return -1;
            if(charset.indexOf(o1) > charset.indexOf(o2))
                return 1;
            return 0;
        }
    }

    /**
     * Creates a new Median Operation, which will apply the Median filter to a given AsciiImage
     */
    public MedianOperation() {
    }

    /**
     *
     * @param img
     *            The AsciiImage to use as basis for executing the Operation, it will remain
     *            unchanged
     * @return A new AsciiImage to which the Median filter has been applied
     * @throws OperationException
     */
    public AsciiImage execute(AsciiImage img) throws OperationException {
        AsciiImage newI = new AsciiImage(img);
        for(int x=0; x<newI.getWidth(); x++) {
            for(int y=0; y<newI.getHeight(); y++) {
                newI.setPixel(x,y,getMedianChar(img,x,y));
            }
        }
        return newI;
    }

    /**
     *
     * @param img The AsciiImage from which the characters are to be taken
     * @param x x coordinate of the pixel in the center of the 3x3 block
     * @param y y coordinate of the pixel in the center of the 3x3 block
     * @return The character with the median brightness value of the 3x3 block
     */
    public char getMedianChar(AsciiImage img, int x, int y) {
        //create a new Comparator to sort the characters according to their brightness value
        Comparator<Character> comp = new CharsetComparator(img.getCharset());
        List<Character> chars = new ArrayList<Character>();
        char brightest = img.getCharset().charAt(img.getCharset().length()-1);
        //Increment x and y coordinates so we get the bottom right corner of the 3x3 box
        x++;
        y++;
        //Starting from bottom right corner we decrement x and y until we reach the top left corner
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
              //if we reach beyond the border during decrementing we add the brightest pixel to the list
              if(x-i < 0 || y-j < 0 || x-i >= img.getWidth() || y-j >= img.getHeight()) {
                  chars.add(brightest);
              }
              //else we add the pixels at the decremented position
              else {
                  chars.add(img.getPixel(x - i, y - j));
              }

            }
        }
        //sort the characters
        Collections.sort(chars, comp);
        return chars.get(4);

    }
}
