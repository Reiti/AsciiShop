import sun.misc.ASCIICaseInsensitiveComparator;

import java.util.ArrayList;
import java.util.List;

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
        clear();
    }

    public AsciiImage(AsciiImage img) {
        this.height = img.getHeight();
        this.width = img.getWidth();
        image = new char[height][width];
        for(int x=0; x<width; x++)
            for(int y=0; y<height; y++)
                setPixel(x,y,img.getPixel(x,y));
    }

    public void setPixel(AsciiPoint p, char c) {
        setPixel(p.getX(), p.getY(), c);
    }
    public void setPixel(int x, int y, char c) {
        image[y][x] = c;
    }

    public char getPixel(AsciiPoint p) {
        return getPixel(p.getX(), p.getY());
    }

    public char getPixel(int x, int y) {
        return image[y][x];
    }

    public List<AsciiPoint> getPointList(char c) {
        List<AsciiPoint> list = new ArrayList<AsciiPoint>();
        for(int x = 0; x<width; x++) {
            for(int y=0; y<height; y++) {
                if(getPixel(x,y) == c)
                    list.add(new AsciiPoint(x,y));
            }
        }
        return list;
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
        for(AsciiPoint p:getPointList(oldChar))
            setPixel(p,newChar);
    }

    /**
     * Returns the centroid of all occurences of the specified character
     * @param c The character to find the centroid
     * @return the centroid, or null if character doesn't occur
     */
    public AsciiPoint getCentroid(char c) {
        List<AsciiPoint> pointList = getPointList(c);
        int count = pointList.size();
        int xS=0;
        int yS=0;
        if(count == 0)
            return null;
        for(AsciiPoint p:pointList) {
            xS+= p.getX();
            yS+= p.getY();
        }
        int cX = (int)Math.round((double)xS/count);
        int cY = (int)Math.round((double)yS/count);

        return new AsciiPoint(cX,cY);
    }

    /**
     * Fills an area of the image using flood-fill
     * @param x x-coordinate of starting point
     * @param y y-coordinate of starting point
     * @param c color to fill with
     */
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

    /**
     * Transposes the image (swap x and y axis)
     */
    public void transpose() {
        char newImage[][] = new char[width][height];
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                //in char array first coordinate is height, hence this is a transpose
                newImage[x][y] = getPixel(x,y);
            }
        }
        int temp = width;
        width = height;
        height = temp;
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

    /**
     * Straightens regions of the image
     * @param c
     */
    public void straightenRegion(char c) {
        boolean changed = false;
        for(AsciiPoint p:getPointList(c)) {
            if(countNeighbors(p,c) <= 1) {
                setPixel(p, '.');
                changed = true;
            }
        }
        if(changed)
            straightenRegion(c);
    }


    /**
     *
     * @param p Point whose neighbors are being counted
     * @param c which neighbor to count
     * @return number of neighbors
     */
    private int countNeighbors(AsciiPoint p, char c) {

        return countNeighbors(p.getX(),p.getY(),c);
    }

    private int countNeighbors(int x, int y, char c) {
        int count = 0;
        if(isWithinBounds(x-1,y) && getPixel(x-1,y) == c)
            count++;
        if(isWithinBounds(x+1,y) && getPixel(x+1,y) == c)
            count++;
        if(isWithinBounds(x,y-1) && getPixel(x,y-1) == c)
            count++;
        if(isWithinBounds(x,y+1) && getPixel(x,y+1) == c)
            count++;
        return count;
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
    public void line(int x0, int y0, int x1, int y1, char c) {
        if(!isWithinBounds(x0, y0) || !isWithinBounds(x1, y1))
            return;

        double deltaX = x1-x0;
        double deltaY = y1-y0;
        //if y distance is greater than x distance
        if(Math.abs(deltaY) > Math.abs(deltaX)) {
            //swap axes before drawing the line
            transpose();
            if(deltaY <= 0)
                drawLine(y1, x1, y0, x0, c);
            else
                drawLine(y0,x0,y1,x1,c);
            //swap back
            transpose();
        }
        else {
            if(deltaX <= 0)
                drawLine(x1,y1,x0,y0,c);
            else
                drawLine(x0,y0,x1,y1,c);
        }
    }

    public void drawLine(int x0, int y0, int x1, int y1, char c) {
        double y = y0;
        double deltaY = y1-y0;
        double deltaX = x1-x0;
        if(x0 != x1 || y0 != y1) {
             for(int x=x0; x<=x1; x++) {
                setPixel(x,(int)Math.round(y),c);
                if(deltaX > 0)
                    y+=deltaY/deltaX;
            }
        }
        else
            setPixel(x0, y0,c);
    }

    /**
     * Grows a region around a specified character
     * @param c The character to grow
     */
    public void growRegion(char c) {
        AsciiImage newImage = new AsciiImage(this);
        for(AsciiPoint p:getPointList(c))
            newImage.grow(p,c);
        this.image = newImage.image;
    }

    private void grow(AsciiPoint p, char c) {
        grow(p.getX(),p.getY(),c);
    }

    /**
     * Grows the current pixel in 4 directions
     */
    private void grow(int x, int y, char c) {
        if(isWithinBounds(x-1,y) && getPixel(x-1,y) == '.')
            setPixel(x-1,y,c);
        if(isWithinBounds(x+1,y) && getPixel(x+1,y) == '.')
            setPixel(x+1,y,c);
        if(isWithinBounds(x,y-1) && getPixel(x,y-1) == '.')
            setPixel(x,y-1,c);
        if(isWithinBounds(x,y+1) && getPixel(x,y+1) == '.')
            setPixel(x,y+1,c);
    }

}
