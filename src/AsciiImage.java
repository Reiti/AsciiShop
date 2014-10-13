/**
 * User: Michael Reitgruber
 * Date: 13.10.2014
 * Time: 16:15
 */
public class AsciiImage {
    private int height;
    private int width;
    private String image;

    public AsciiImage() {
        height = 0;
        width = 0;
        image = "";
    }

    /**
     *
     * @param line The line to be added to the image
     * @return true if same line length as other lines, else false
     */
    public boolean addLine(String line) {
        if(line.length() > 0) {
            if(width == 0)
                width = line.length();
            if(line.length() == width) {
                height++;
                image+=line;
                return true;
            }
        }
        return false;
    }
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Fills an area of a certain color with a new color
     * @param x x-coordinate of start pixel
     * @param y y-coordinate of start pixel
     * @param c color to fill with
     */
    public void fill(int x, int y, char c) {
        char color = getPixel(x,y);
        setPixel(x,y,c);

        //recursively call fill on neighboring pixels of the same color
        if(isWithinBounds(x-1,y) && getPixel(x-1,y) == color)
            fill(x-1,y,c);
        if(isWithinBounds(x+1,y) && getPixel(x+1,y) == color)
            fill(x+1,y,c);
        if(isWithinBounds(x,y-1) && getPixel(x,y-1) == color)
            fill(x,y-1,c);
        if(isWithinBounds(x,y+1) && getPixel(x,y+1) == color)
            fill(x,y+1,c);
    }

    private char getPixel(int x, int y) {
        return image.charAt(y*width+x);
    }

    private void setPixel(int x, int y, char c) {
        image = image.substring(0, y * width + x) + c + image.substring(y * width + x + 1);
    }

    /**
     * Transposes the image
     * (=swap rows and columns)
     */
    public void transpose() {
        int newWidth = height;
        int newHeight = width;
        String newImage = "";
        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {    //iterate over all chars in one column
                newImage += getPixel(x,y);        //and add them to one line
            }
        }
        image = newImage;

        //flip heigth and width for the transposed image
        height = newHeight;
        width = newWidth;
    }

    /**
     * Flips the image vertically
     */
    public void flipV() {
        String newImage = "";
        for(int y = height-1; y >= 0; y--) {
            for(int x = 0; x < width; x++) {
                newImage+=getPixel(x,y);        //add last line of old image to new
            }
        }
        image = newImage;
    }

    /**
     * Calculate the number of unique chars in the image
     * @return the number of unique chars
     */
    public int getUniqueChars() {
        String uniqueChars = "";
        for(char c:image.toCharArray()) {
            if(!uniqueChars.contains(String.valueOf(c)))           //checks if the character was already encountered
                uniqueChars+=c;                                    //if not add it to list of encountered characters
        }
        return uniqueChars.length();
    }

    /**
     * Checks if the image is horizontally symmetric
     * @return true if the image is symmetric, else false
     */
    public boolean isSymmetricH() {
        for(int line = 0; line < height; line ++) {
            //iterate over the line from left to right and from right to left
            //stop once they passed the middle of the line
            for(int left=0, right = width-1; left<=right; left++,right--) {
                //if the pixels don't have the same color, the picture is no symmetrical
                if(getPixel(left,line) != getPixel(right, line)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        String s = "";
        for(int y = 0; y < height; y++) {
           for(int x = 0; x < width; x++) {
               s+=getPixel(x,y);
           }
           s+="\n";
        }
        s+=width + " " + height;
        return s;
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
