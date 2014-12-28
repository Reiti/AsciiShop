import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Date: 28.12.2014
 * Time: 21:17
 */
public class LoadFactory implements Factory {

    public LoadFactory() {
    }

    public Operation create(Scanner scanner) throws FactoryException {
        if(!scanner.hasNext())
            throw new FactoryException("Not enough parameters");
        String eof = scanner.next();
        String data = "";
        while(scanner.hasNext()) {
            String line = scanner.next();
            if(line.trim().equals(eof))
                break;
            data += line+"\n";
        }
        return new LoadOperation(data);
    }
}
