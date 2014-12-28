/**
 * User: Michael Reitgruber
 * Date: 28.12.2014
 * Time: 21:05
 */
public class BinaryOperation implements Operation {

    private char threshold;

    /**
     *
     * @param threshold The threshold character to be used for filtering the image
     */
    public BinaryOperation(char threshold) {
        this.threshold = threshold;
    }

    /**
     *
     * @param img
     *            The AsciiImage to use as basis for executing the Operation, it will remain
     *            unchanged
     * @return A new AsciiImage with the Binary Filter applied (all chars below the threshold replaced with the darkest,
     * and all chars from the threshold replaced with the brightest character
     * @throws OperationException
     */
    public AsciiImage execute(AsciiImage img) throws OperationException {
        if(img.getCharset().indexOf(threshold) < 0)
            throw new OperationException("Threshold character not in charset");
        String charset = img.getCharset();
        int thIndex = charset.indexOf(threshold);
        AsciiImage newI = new AsciiImage(img);
        for(int x=0; x<newI.getWidth(); x++) {
            for (int y = 0; y < newI.getHeight(); y++) {
                if (charset.indexOf(img.getPixel(x, y)) < thIndex)
                    newI.setPixel(x, y, charset.charAt(0));
                else
                    newI.setPixel(x, y, charset.charAt(charset.length() - 1));

            }
        }
        return newI;
    }
}
