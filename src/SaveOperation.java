/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:43
 */
public class SaveOperation implements Operation {

    private MetricSet<AsciiImage> saved;

    public SaveOperation(MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    public AsciiImage execute(AsciiImage img) throws OperationException {
        saved.add(img);
        return new AsciiImage(img);
    }
}
