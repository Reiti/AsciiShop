import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 28.12.2014
 * Time: 21:14
 */
public class ClearFactory implements Factory {

    public ClearFactory() {
    }

    /**
     *
     * @param scanner
     *            The Scanner to use for reading parameters
     * @return A new Clear Operation
     * @throws FactoryException
     */
    public Operation create(Scanner scanner) throws FactoryException {
        return new ClearOperation();
    }
}
