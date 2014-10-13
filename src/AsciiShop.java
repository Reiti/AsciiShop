import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Checks if a entered Ascii Image is formatted correctly
 */
public class AsciiShop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int width = 0;
        int height= 0;
        if(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            width = input.length();             //get length of first line for later comparison
            height++;
        }
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.length()!=width) {          //print INPUT MISMATCH and return if length is not equal
                System.out.println("INPUT MISMATCH");
                return;
            }
            height++;
        }

        System.out.println(width + " " + height);
    }

}
