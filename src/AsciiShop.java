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
        AsciiImage image;
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
        }
        if(height <= 0 || width <= 0) {
            System.out.println("INPUT MISMATCH");
            return;
        }
        image = new AsciiImage(width, height);


        while(scanner.hasNext()) {
            String command = scanner.next();
            if(command.equals("load")) {
                if(!scanner.hasNext()){
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                String eof = scanner.next();
                int lines = 0;
                while(scanner.hasNext()) {
                    String line = scanner.next();
                    if(line.trim().equals(eof))
                        break;
                    if(line.trim().length()!=width) {
                        System.out.println("INPUT MISMATCH");
                        return;
                    }
                    image.addLine(line.trim(), lines);
                    lines++;
                }
                if(lines!=height) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
            }
            else if(command.equals("fill")) {
                int x = readInt(scanner);
                if(x == -1) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }

                int y = readInt(scanner);
                if(y == -1) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }

                char c = readChar(scanner);
                if(c == ' ') {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                if(x >= image.getWidth() || y >= image.getHeight()) {
                    System.out.println("OPERATION FAILED");
                    return;
                }
                image.fill(x, y, c);
            }
            else if(command.equals("transpose")) {
                image.transpose();
            }
            else if(command.equals("print")) {
                System.out.println(image.toString());
            }
            else if(command.equals("clear")) {
                image.clear();
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
                image.replace(oldC, newC);
            }
            else if(command.equals("line")) {
                if(!scanner.hasNextInt()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int x0 = scanner.nextInt();
                if(!scanner.hasNextInt()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int y0 = scanner.nextInt();
                if(!scanner.hasNextInt()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int x1 = scanner.nextInt();
                if(!scanner.hasNextInt()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int y1 = scanner.nextInt();
                if(!scanner.hasNext()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                char c = scanner.next().charAt(0);
                image.line(x0, y0, x1, y1, c);
            }
            else if(command.equals("create")) {
                System.out.println("UNKNOWN COMMAND");
                return;
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
