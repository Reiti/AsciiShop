import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 28.12.2014
 * Time: 20:58
 */
public interface Factory {
    /**
     *
     * @param scanner
     *            The Scanner to use for reading parameters
     * @throws FactoryException
     */
    public Operation create(Scanner scanner) throws FactoryException;
}
