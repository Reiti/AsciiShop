import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 28.12.2014
 * Time: 21:15
 */
public class FilterFactory implements Factory {

    public FilterFactory() {
    }

    /**
     *
     * @param scanner
     *            The Scanner to use for reading parameters
     * @return A new MedianOperation
     * @throws FactoryException
     */
    public Operation create(Scanner scanner) throws FactoryException {
        if(!scanner.hasNext())
            throw new FactoryException("Not enough parameters");
        String type = scanner.next();
        if(!type.equals("median"))
            throw new FactoryException("Wrong filter type");
        return new MedianOperation();
    }
}
