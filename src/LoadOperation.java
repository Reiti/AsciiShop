/**
 * User: Michael Reitgruber
 * Date: 23.12.2014
 * Time: 19:35
 */
public class LoadOperation implements Operation {

    private String data;

    /**
     * Creates a new Load Operation, which will fill a given Ascii Image with data
     * @param data The content of the image
     */
    public LoadOperation(String data) {
        this.data = data;
    }

    /**
     *
     * @param img
     *            The AsciiImage to use as basis for executing the Operation, it will remain
     *            unchanged
     * @return A new AsciiImage filled with the given data
     * @throws OperationException
     */
    public AsciiImage execute(AsciiImage img) throws OperationException {
        AsciiImage newI = new AsciiImage(img);
        String[] lines = data.split("\n");

        if(lines.length < img.getHeight()) {
            throw new OperationException("Not enough lines");
        }

        for(int y = 0; y < lines.length; y++) {

            if(lines[y].length() < img.getWidth()) {
                throw new OperationException("Line too short");
            }

            for(int x = 0; x < lines[y].length(); x++) {

                if(img.getCharset().indexOf(lines[y].charAt(x)) < 0) {
                    throw new OperationException("Invalid character");
                }

                newI.setPixel(x,y,lines[y].charAt(x));
            }
        }
        return newI;
    }
}
