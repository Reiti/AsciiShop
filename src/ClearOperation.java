/**
 * User: Michael Reitgruber
 * Date: 23.12.2014
 * Time: 19:34
 */
public class ClearOperation implements Operation {

    /**
     * Creates a new clear operation, that will return an image with all pixels set to the brightest value
     */
    public ClearOperation() {
    }


    /**
     *
     * @param img
     *            The AsciiImage to use as basis for executing the Operation, it will remain
     *            unchanged
     * @return A new AsciiImage with all pixels set to the brightest value
     * @throws OperationException
     */
    public AsciiImage execute(AsciiImage img) throws OperationException {
        return new AsciiImage(img.getWidth(), img.getHeight(), img.getCharset());
    }
}
