import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 03.01.2015
 * Time: 15:47
 */
public class CreateFactory implements Factory {

    public CreateFactory() {
    }

    public Operation create(Scanner scanner) throws FactoryException {
        int height;
        int width;
        String charset;
        if(!scanner.hasNextInt()) {
            throw new FactoryException("Not enough parameters");
        }
        width = scanner.nextInt();
        if(!scanner.hasNextInt()) {
            throw new FactoryException("Not enough parameters");
        }
        height = scanner.nextInt();
        if(!scanner.hasNext()) {
            throw new FactoryException("Not enough parameters");
        }
        charset = scanner.next();
        return new CreateOperation(width,height,charset);
    }
}
