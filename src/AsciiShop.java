import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Checks if a entered Ascii Image is formatted correctly
 */
public class AsciiShop {
    public static void main(String[] args) {
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
            if(command.equals("load")) {
                Operation load = null;
                if(!scanner.hasNext()){
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                stack.push(new AsciiImage(image));
                String eof = scanner.next();
                String data = "";
                while(scanner.hasNext()) {
                    String line = scanner.next();
                    if(line.trim().equals(eof))
                        break;
                    data += line+"\n";
                }
                load = new LoadOperation(data);
                try {
                    image = load.execute(image);
                } catch (OperationException e) {
                    System.out.println("OPERATION FAILED");
                    return;
                }
            }
            else if(command.equals("print")) {
                System.out.println(image.toString());
            }
            else if(command.equals("clear")) {
                stack.push(new AsciiImage(image));
                Operation clear = new ClearOperation();
                try {
                    image = clear.execute(image);
                } catch (OperationException e) {
                    System.out.println("OPERATION FAILED");
                    return;
                }
            }
            else if(command.equals("replace")) {
                if(!scanner.hasNext()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                char oldC = scanner.next().charAt(0);
                if(!scanner.hasNext()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                char newC = scanner.next().charAt(0);
                stack.push(new AsciiImage(image));
                Operation replace = new ReplaceOperation(oldC, newC);
                try {
                    image = replace.execute(image);
                } catch (OperationException e) {
                    System.out.println("OPERATION FAILED");
                    return;
                }
            }
            else if(command.equals("create")) {
                System.out.println("UNKNOWN COMMAND");
                return;
            }
            else if(command.equals("undo")) {
                AsciiImage tempImage = stack.pop();
                image = tempImage;
                if(tempImage == null)
                    System.out.println("STACK EMPTY");
            }
            else if(command.equals("filter")) {
                if(!scanner.hasNext()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                String type = scanner.next();
                if(!type.equals("median")) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                stack.push(new AsciiImage(image));
                Operation median = new MedianOperation();
                try {
                    image = median.execute(image);
                } catch (OperationException e) {
                    System.out.println("OPERATION FAILED");
                    return;
                }

            }
            else {                                        //exit program if invalid command is entered
                System.out.println("UNKNOWN COMMAND");
                return;
            }
        }
    }

    /**
     *
     * @param scanner Scanner to read input from
     * @return the read int or -1 if failed to read int
     */
    public static int readInt(Scanner scanner) {
        if(scanner.hasNextInt())
            return scanner.nextInt();
        return -1;
    }

    /**
     *
     * @param scanner Scanner to read input from
     * @return the read char or a space if failed to read char
     */
    public static char readChar(Scanner scanner) {
        if(scanner.hasNext()) {
            String input = scanner.next();
            if(input.length() == 1)
                return input.charAt(0);
        }
        return ' ';
    }


    /**
     * @param scanner Scanner to read input from
     * @return the height of the picture
     */
    public static int getReadCommand(Scanner scanner) {
        int height = 0;
        if (scanner.hasNext()) {
            if (!scanner.next().equals("read")) {        //first command has to be read
                return -1;
            }
            if (!scanner.hasNextInt()) {                //read has to be followed by an int
                return -1;
            }
            return scanner.nextInt();
        }
        return -1;
    }

}
