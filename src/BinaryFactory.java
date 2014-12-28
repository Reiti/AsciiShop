import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 28.12.2014
 * Time: 21:12
 */
public class BinaryFactory implements Factory {

    public BinaryFactory() {

    }

    /**
     *
     * @param scanner
     *            The Scanner to use for reading parameters
     * @return A new BinaryOperation with the specified threshold character
     * @throws FactoryException
     */
    public Operation create(Scanner scanner) throws FactoryException {
        if(!scanner.hasNext())
            throw new FactoryException("Not enough parameters");
        char t = scanner.next().charAt(0);
        return new BinaryOperation(t);
    }
}
