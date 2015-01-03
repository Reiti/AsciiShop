import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Checks if a entered Ascii Image is formatted correctly
 */
public class AsciiShop {

    private static HashMap<String, Factory> operations = new HashMap<String, Factory>();
    private static MetricSet<AsciiImage> saved = new MetricSet<AsciiImage>();
    public static void main(String[] args) {
        initOperations();
        Scanner scanner = new Scanner(System.in);
        AsciiImage image = null;
        AsciiStack stack = new AsciiStack();


        while(scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("undo")) {
                AsciiImage tempImage = stack.pop();
                if (tempImage == null)
                    System.out.println("STACK EMPTY");
                else
                    image = tempImage;
            } else if (command.equals("print")) {
                if(image!=null)
                    System.out.println(image.toString());
            } else if(command.equals("histogram")) {
                AsciiImage hist = Histogram.getHistogram(image);
                System.out.println(hist.toString());
            } else if(command.equals("printsaved")) {
                if(saved.size()==0)
                    System.out.println("NO SAVED IMAGES");
                for (AsciiImage aSaved : saved)
                    System.out.println(aSaved.toString());
            } else {
                if(image!=null)
                    stack.push(new AsciiImage(image));
                if (operations.get(command) != null) {
                    try {
                        image = operations.get(command).create(scanner).execute(image);
                    } catch (OperationException e) {
                        System.out.println("OPERATION FAILED");
                        return;
                    } catch (FactoryException e) {
                        System.out.println("INPUT MISMATCH");
                        return;
                    }
                } else {
                    System.out.println("UNKNOWN COMMAND");
                }
            }


        }
    }

    private static void initOperations() {
        operations.put("binary", new BinaryFactory());
        operations.put("clear", new ClearFactory());
        operations.put("filter", new FilterFactory());
        operations.put("load", new LoadFactory());
        operations.put("replace", new ReplaceFactory());
        operations.put("create", new CreateFactory());
        operations.put("save", new SaveFactory(saved));
        operations.put("search", new SearchFactory(saved));
    }

}
