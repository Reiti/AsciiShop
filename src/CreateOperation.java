/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:42
 */
public class CreateOperation implements Operation {

    private int width;
    private int height;
    private String charset;

    public CreateOperation(int width, int height, String charset) {
        this.width = width;
        this.height = height;
        this.charset = charset;
    }

    public AsciiImage execute(AsciiImage img) throws OperationException {
        return new AsciiImage(width, height, charset);
    }
}
