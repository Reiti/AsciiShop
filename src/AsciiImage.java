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
    private String charset;

    public AsciiImage(int width, int height, String charset) {
        this.width = width;
        this.height = height;
        image = new char[height][width];
        this.charset = charset;
        create();
    }

    private void create() {
        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                setPixel(x,y,charset.charAt(charset.length()-1));
            }
        }
    }

    public AsciiImage(AsciiImage img) {
        this.height = img.getHeight();
        this.width = img.getWidth();
        this.charset = img.getCharset();
        image = new char[height][width];
        for(int x=0; x<width; x++)
            for(int y=0; y<height; y++)
                setPixel(x,y,img.getPixel(x,y));
    }

    public void setPixel(AsciiPoint p, char c) throws IndexOutOfBoundsException{
        setPixel(p.getX(), p.getY(), c);
    }
    public void setPixel(int x, int y, char c) throws IndexOutOfBoundsException{
        if(!isWithinBounds(x,y) || charset.indexOf(c) < 0) {
            throw new IndexOutOfBoundsException();
        }
        image[y][x] = c;
    }

    public char getPixel(AsciiPoint p) throws IndexOutOfBoundsException {
        return getPixel(p.getX(), p.getY());
    }

    public char getPixel(int x, int y) throws IndexOutOfBoundsException {
        if(!isWithinBounds(x,y)) {
            System.out.println("x: "+x+" y: "+y);
            System.out.println("width: "+width +" height: "+height);
            throw new IndexOutOfBoundsException();
        }
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


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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


    public String getCharset() {
        return charset;
    }

    public boolean equals(Object o) {
        if(!(o instanceof AsciiImage))
            return false;
        AsciiImage co = (AsciiImage)o;
        if(co.getHeight() != height || co.getWidth() != width)
            return false;
        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                if(getPixel(x,y) != co.getPixel(x,y))
                    return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int sum = 0;
        for(int x = 0; x<width; x++)
            for(int y=0; y<height; y++)
                sum+=getPixel(x,y);
        return sum;
    }

    public int getUniqueChars() {
        String unique = "";
        for(int x = 0; x<width; x++) {
            for (int y = 0; y < height; y++) {
                if (unique.indexOf(getPixel(x, y)) < 0)
                    unique += getPixel(x, y);
            }
        }
        return unique.length();
    }
}
