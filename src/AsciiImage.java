/**
 * User: Michael Reitgruber
 * Date: 13.10.2014
 * Time: 16:15
 */


public class AsciiImage {
    private char[][] image;
    private int width;
    private int height;

    public AsciiImage(int width, int height) {
        this.width = width;
        this.height = height;
        image = new char[height][width];
    }

    public void setPixel(int x, int y, char c) {
        image[y][x] = c;
    }

    public char getPixel(int x, int y) {
        return image[y][x];
    }

    public void clear() {
        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                setPixel(x,y,'.');
            }
        }
    }

    public void addLine(String line, int lineNumber) {
        for(int i=0; i<line.length(); i++) {
            setPixel(i, lineNumber, line.charAt(i));
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void replace(char oldChar, char newChar) {
        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                if(getPixel(x,y) == oldChar)
                    setPixel(x,y,newChar);
            }
        }
    }

    public void fill(int x, int y, char c) {
        char oldC = getPixel(x,y);
        setPixel(x,y,c);

        if(isWithinBounds(x+1,y) && getPixel(x + 1, y) == oldC)
            fill(x+1, y, c);
        if(isWithinBounds(x-1, y) && getPixel(x-1, y) == oldC)
            fill(x-1, y, c);
        if(isWithinBounds(x, y+1) && getPixel(x, y+1) == oldC)
            fill(x, y+1, c);
        if(isWithinBounds(x, y-1) && getPixel(x, y-1) == oldC)
            fill(x, y-1, c);
    }

    public void transpose() {
        char newImage[][] = new char[height][width];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                //in char array first coordinate is height, hence this is a transpose
                newImage[x][y] = getPixel(x,y);
            }
        }

        image = newImage;
    }

    @Override
    public String toString() {
        String out = "";
        for(int line = 0; line<height; line++) {
            out += new String(image[line]);
            out += "\n";
        }
        return out;
    }

    public boolean isWithinBounds(int x, int y) {
        return !(x >= width || x < 0 || y >= height || y < 0);
    }

    /**
     *
     * @param x0 x-coordinate of start point
     * @param y0 y-coordinate of start point
     * @param x1 x-coordinate of end point
     * @param y1 y-coordinate of end point
     * @param c character to draw the line with
     * @return true if coordinates within bounds, else false
     */
    public boolean line(int x0, int y0, int x1, int y1, char c) {
        if(!isWithinBounds(x0, y0) || !isWithinBounds(x1, y1))
            return false;

        int deltaX = x0-x1;
        int deltaY = y0-y1;
        boolean axesSwap = false;
        if(Math.abs(deltaY) > Math.abs(deltaX)) {
            //Swap y1 with x1, y0 with x0 (swaps axes)
            int t = x0;
            x0 = y0;
            y0 = t;
            t = x1;
            x1 = y1;
            y1 = t;
            axesSwap = true;
        }
        if(x1 < x0) {
            //swap start and end point if start is further right than left
            int t = x0;
            x0 = x1;
            x1 = t;
            t = y0;
            y0 = y1;
            y1 = t;
        }
        System.out.println(x0 + " " + y0 + " " + x1 + " " + y1);
        int y = y0;
        for(int x=x0; x < x1; x++) {
            //swap x and y if the axes have been swapped
            if(axesSwap)
                setPixel(y,x,c);
            else
                setPixel(x,y,c);
            if(deltaX != 0)
                y += deltaY/deltaX;
        }
        return true;
    }

}
