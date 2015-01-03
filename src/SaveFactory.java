import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:52
 */
public class SaveFactory implements Factory {

    private MetricSet<AsciiImage> saved;
    public SaveFactory(MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    public Operation create(Scanner scanner) throws FactoryException {
        return new SaveOperation(saved);
    }
}
