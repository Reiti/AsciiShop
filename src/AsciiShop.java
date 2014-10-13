import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Checks if a entered Ascii Image is formatted correctly
 */
public class AsciiShop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AsciiImage image = new AsciiImage();

        int height = getReadCommand(scanner);           //read the read command
        if(height == -1) {
            System.out.println("INPUT MISMATCH");
            return;
        }

        for(int lines = 0; lines < height; lines++) {
            if(!scanner.hasNext()) {                    //check if too few lines are entered
                System.out.println("INPUT MISMATCH");
                return;
            }
            if(!image.addLine(scanner.next())) {
                System.out.println("INPUT MISMATCH");
                return;
            }
        }

        while(scanner.hasNext()) {
            String command = scanner.next();
            if(command.equals("fill")) {
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
                image.fill(x,y,c);
            }
            else if(command.equals("transpose")) {
                image.transpose();
            }
            else if(command.equals("flip-v")) {
                image.flipV();
            }
            else if(command.equals("uniqueChars")) {
                int uc = image.getUniqueChars();
                System.out.println(uc);
            }
            else if(command.equals("symmetric-h")) {
                System.out.println(image.isSymmetricH());
            }
            else {                                        //exit program if invalid command is entered
                System.out.println("INPUT MISMATCH");
                return;
            }
        }
        System.out.println(image.toString());
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
     * @param scanne rScanner to read input from
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
