import java.util.*;

/**
 * User: Michael Reitgruber
 * Date: 23.12.2014
 * Time: 19:35
 */
public class MedianOperation extends FilterOperation {

    /**
     * Creates a new Median Operation, which will apply the Median filter to a given AsciiImage
     */
    public MedianOperation(BlockGenerator gen) {
        super(gen);
    }



    @Override
    public int filter(int[] values) {
        Arrays.sort(values);
        return values[values.length/2];
    }

}
