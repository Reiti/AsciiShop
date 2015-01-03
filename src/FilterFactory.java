import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
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
        String type;
        int size = 3;
        String borderTreatment = "x";
        BlockGenerator gen;
        if(!scanner.hasNextLine())
            throw new FactoryException("Not enough parameters");
        String[] parameters = scanner.nextLine().substring(1).split(" ");
        if(parameters.length == 0)
            throw new FactoryException("Not enough parameters");

        type = parameters[0];

        if(parameters.length == 2) {
            try {
                size = Integer.parseInt(parameters[1]);
            } catch(NumberFormatException e) {
                borderTreatment = parameters[1];
            }
        }
        if(parameters.length == 3) {
            size = Integer.parseInt(parameters[1]);
            borderTreatment = parameters[2];
        }

        if(borderTreatment.equals("x"))
            gen = new XBlockGenerator(size);
        else if(borderTreatment.equals("circular"))
            gen = new CircularBlockGenerator(size);
        else if(borderTreatment.equals("replicate"))
            gen = new ReplicateBlockGenerator(size);
        else if(borderTreatment.equals("symmetric"))
            gen = new SymmetricBlockGenerator(size);
        else
            throw new FactoryException("Invalid Border Treatment Type");

        if(type.equals("average"))
            return new AverageOperation(gen);
        else if(type.equals("median"))
            return new MedianOperation(gen);
        else
            throw new FactoryException("Invalid Filter type");
    }
}
