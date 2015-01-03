/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 13:34
 */
public class AverageOperation extends FilterOperation {

    public AverageOperation(BlockGenerator gen) {
        super(gen);
    }

    @Override
    public int filter(int[] values) {
        int runningSum = 0;
        for(int val:values)
            runningSum+=val;
        return (int) Math.round((double) runningSum / (double) (values.length));
    }
}
