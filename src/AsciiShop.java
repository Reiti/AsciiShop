import java.util.HashMap;
import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Checks if a entered Ascii Image is formatted correctly
 */
public class AsciiShop {

    private static HashMap<String, Factory> operations = new HashMap<String, Factory>();

    public static void main(String[] args) {
        initOperations();
        Scanner scanner = new Scanner(System.in);
        int height = 0;
        int width = 0;
        String charset = null;
        AsciiImage image;
        AsciiStack stack = new AsciiStack();

        if(scanner.hasNext()) {
            if(!scanner.next().equals("create")) {
                System.out.println("INPUT MISMATCH");
                return;
            }
            if(!scanner.hasNextInt()) {
                System.out.println("INPUT MISMATCH");
                return;
            }
            width = scanner.nextInt();
            if(!scanner.hasNextInt()) {
                System.out.println("INPUT MISMATCH");
                return;
            }
            height = scanner.nextInt();
            if(!scanner.hasNext()) {
                System.out.println("INPUT MISMATCH");
                return;
            }
            charset = scanner.next();
        }
        if(height <= 0 || width <= 0) {
            System.out.println("INPUT MISMATCH");
            return;
        }
        image = new AsciiImage(width, height, charset);



        while(scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("undo")) {
                AsciiImage tempImage = stack.pop();
                if (tempImage == null)
                    System.out.println("STACK EMPTY");
                else
                    image = tempImage;
            } else if (command.equals("print")) {
                System.out.println(image.toString());
            } else if(command.equals("histogram")) {
                AsciiImage hist = Histogram.getHistogram(image);
                System.out.println(hist.toString());
            } else {
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
    }

}
