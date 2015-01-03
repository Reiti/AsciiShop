/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 13:35
 */
public abstract class FilterOperation implements Operation {
    protected BlockGenerator gen;
    public FilterOperation(BlockGenerator gen) {
        this.gen = gen;

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
                newI.setPixel(x,y,img.getCharset().charAt(filter(gen.getBlock(img,x,y))));
            }
        }
        return newI;
    }

    public abstract int filter(int[] values);
}
