import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:54
 */
public class SearchFactory implements Factory {

    private MetricSet<AsciiImage> saved;

    public SearchFactory(MetricSet<AsciiImage> saved) {
        this.saved = saved;
    }

    public Operation create(Scanner scanner) throws FactoryException {
        if(!scanner.hasNext())
            throw new FactoryException("Not enough parameters");
        String metric = scanner.next();
        if(metric.equals("pixelcount")) {
            return new SearchOperation(saved, new PixelCountMetric());
        }
        else if(metric.equals("uniquechars")) {
            return new SearchOperation(saved, new UniqueCharsMetric());
        }
        else {
            throw new FactoryException("Invalid metric");
        }
    }
}
