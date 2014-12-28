import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: Michael Reitgruber
 * Date: 28.12.2014
 * Time: 21:36
 */
public class Histogram {
    public static AsciiImage getHistogram(AsciiImage img) {
        HashMap<Character,Integer> freqMap = new HashMap<Character, Integer>();

        String oldCharset = img.getCharset();
        String newCharset;
        newCharset = oldCharset;
        for(int i = 0; i<=9; i++) {
            if (oldCharset.indexOf(i + '0') < 0)
                newCharset = i + newCharset;
        }
        if(oldCharset.indexOf('.') < 0)
            newCharset = newCharset + '.';
        if(oldCharset.indexOf('#') < 0)
            newCharset = '#' + newCharset;
        int width = 3+oldCharset.length();
        AsciiImage histo = new AsciiImage(width, 16, newCharset);

        List<Integer> heights = new ArrayList<Integer>();
        int max = 0;
        Character mostFrequent = oldCharset.charAt(0);

        //find the most frequent character
        for(Character c:oldCharset.toCharArray()) {
            int count = img.getPointList(c).size();
            if(count > max) {
                max = count;
                mostFrequent = c;
            }
            freqMap.put(c,count);
        }

        for(Character c:oldCharset.toCharArray()) {
            heights.add(getHeight(freqMap.get(c), freqMap.get(mostFrequent)));
        }

        //build the strings that make up the scale
        List<String> scaleStrings = buildScale(freqMap.get(mostFrequent), img.getHeight()*img.getWidth());
        String image = "";
        //pad the strings to the width of the image and concatenate them to one image
        for (String scaleString : scaleStrings) {
            String s = pad(scaleString, width);
            image += s + "\n";
        }
        Operation o = new LoadOperation(image);
        try {
            //fill the histogram with the scale
            histo = o.execute(histo);
        } catch (OperationException e) {
            e.printStackTrace();
        }
        insertBars(histo, heights);
        insertCharset(histo, oldCharset);
        //flip the histogram, because it is upside down the way we created it now
        return flip(histo);
    }

    /**
     * Flips a histogram vertically
     * @param histo The histogram to be flipped
     * @return The vertically flipped histogram
     */
    private static AsciiImage flip(AsciiImage histo) {
        AsciiImage newI = new AsciiImage(histo);
        for(int x=0; x<histo.getWidth(); x++) {
            for(int y=0; y<histo.getHeight(); y++) {
                newI.setPixel(x,histo.getHeight()-y-1,histo.getPixel(x,y));
            }
        }
        return newI;
    }

    /**
     * Inserts the charset line into the histogram
     */
    private static void insertCharset(AsciiImage histo, String oldCharset) {
        for(int i=0; i<oldCharset.length(); i++) {
            histo.setPixel(3+i,0,oldCharset.charAt(i));
        }
    }

    /**
     * Inserts the individual bars into the histogram
     */
    private static void insertBars(AsciiImage histo, List<Integer> heights) {
        for(int i=0; i<heights.size(); i++) {
            for(int y=0; y<heights.get(i); y++) {
                //i+3 because we skip the scale to the left, y+1 because we skip the line with the charset
                histo.setPixel(i+3, y+1,'#');
            }
        }
    }

    /**
     *
     * pads a given string to a given length to the right
     */
    private static String pad(String s, int width) {
        int l = s.length();
        for(int i=0; i<(width-l); i++) {
            s+=".";
        }
        return s;
    }

    private static List<String> buildScale(int mfreq, int total) {
        List<String> strings = new ArrayList<String>();
        double perc = (100 / (double)total) * (double)mfreq;
        for(int i=0; i<16; i++) {
            String line = "";
            if(i%2==1) {
                int percLine = (int) Math.round(((double) perc / 15) * i);
                int pad = 3-String.valueOf(percLine).length();
                for(int c = 0; c<pad; c++)
                    line+=".";
                line+=String.valueOf(percLine);
            }
            else
                line = "...";
            strings.add(line);
        }
        return strings;
    }

    /**
     *
     * @param rel The frequency of the character, whose bar we want to draw
     * @param max The frequency of the most frequent character
     * @return The height of the bar
     */
    private static int getHeight(int rel, int max) {
        int r = (int) Math.ceil((15 / (double) max) * (double) rel);
        return r;
    }
}
