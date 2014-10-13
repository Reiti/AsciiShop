import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Checks if a entered Ascii Image is formatted correctly
 */
public class AsciiShop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int width = -1;
        int height= 0;
        String[] image = null;
        if(scanner.hasNext()) {
            if(!scanner.next().equals("read")) {        //first command has to be read
                System.out.println("INPUT MISMATCH");
                return;
            }
            if(!scanner.hasNextInt()) {                //read has to be followed by an int
                System.out.println("INPUT MISMATCH");
                return;
            }
            height = scanner.nextInt();
        }

        image = new String[height];

        for(int line=0; line<height; line++) {
            if(!scanner.hasNext()) {                    //exit program if not enough lines are input
                System.out.println("INPUT MISMATCH");
                return;
            }
            String input = scanner.next();
            if(input.startsWith("fill")) {              //exit program if too few lines are entered
                System.out.println("INPUT MISMATCH");
                return;
            }
            if(width<0) {
                width = input.length();
            }

            if(input.length() != width) {
                System.out.println("INPUT MISMATCH");
                return;
            }
            image[line] = input;
        }

        while(scanner.hasNext()) {
            String command = scanner.next();
            if(command.equals("fill")) {
                if(!scanner.hasNextInt()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int x = scanner.nextInt();
                if(!scanner.hasNextInt()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                int y = scanner.nextInt();
                if(!scanner.hasNext()) {
                    System.out.println("INPUT MISMATCH");
                    return;
                }
                char c = scanner.next().charAt(0);
                if(x > width || x < 0 || y > height || y < 0) {     //check if coordinates for fill are
                    System.out.println("OPERATION FAILED");         //within boundaries
                    return;
                }
                fill(image,x,y,c);
            }
            else {                                                  //exit program if invalid commands are entered
                System.out.println("INPUT MISMATCH");
                return;
            }
        }

        for(String l:image) {
            System.out.println(l);
        }
        System.out.println(width + " " + height);
    }

    public static void fill(String[] image, int x, int y, char c) {
        char replace = getPixel(image,x,y);                   //color to replace
        String newLine = image[y].substring(0,x) + c + image[y].substring(x+1);  //new String with replaced character

        image[y] = newLine;                                   //insert line into image
        if(x-1 >= 0 && getPixel(image,x-1,y) == replace) {    //check if neighboring pixel exists and is the right color
            fill(image,x-1,y,c);
        }
        if(x+1 < image[y].length() && getPixel(image, x + 1, y) == replace) {
            fill(image,x+1,y,c);
        }
        if(y-1 >=0 && getPixel(image, x, y - 1) == replace) {
            fill(image,x,y-1,c);
        }
        if(y+1 < image.length && getPixel(image, x, y + 1) == replace) {
            fill(image,x,y+1,c);
        }
    }

    public static char getPixel(String[] image, int x, int y) {
        return image[y].charAt(x);
    }
}
