import java.util.Scanner;

/**
 * User: Michael Reitgruber
 * Utility to draw horizontal bar diagrams
 */
public class BarPlot {
    private static final int MAX_LABEL_LENGTH = 8;
    private static final int MAX_BAR_LENGTH = 30;
    private static final double MAX_BAR_SCALE= 1.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String label = scanner.next();                      //first value entered is label
            if(scanner.hasNextInt()) {                          //check if next input is integer
                int length = scanner.nextInt();
                if(length > MAX_BAR_LENGTH || length < 0) {     //check if input is within range
                    System.out.println("INPUT ERROR");
                    return;
                }
                System.out.println(drawBar(label, length));
            }

            else if(scanner.hasNextDouble()) {                   //check if input is a double
                double scale = scanner.nextDouble();
                if(scale > MAX_BAR_SCALE || scale < 0) {        //check if input is within range
                    System.out.println("INPUT ERROR");
                    return;
                }
                System.out.println(drawBar(label, scale));
            }
            else {                                              //if input is neither int nor double, exit program
                System.out.println("INPUT ERROR");
                return;
            }

        }
    }

    static String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder(MAX_BAR_LENGTH);  //maximum number of repeated chars is the length of the bar
        for(int length=0; length<n; length++) {
            sb.append(c);
        }
        return sb.toString();
    }

    static String drawLabel(String label, int n) {
        if(label.length()<=n) {
            int diff = n - label.length();                      //if the label is shorter than the max length
            return label+repeat(' ', diff);                     //fill the remainder with spaces
        }
        return label.substring(0,n);
    }

    static String drawBar(String label, int value) {
        int diff = MAX_BAR_LENGTH - value;
        //build bars by appending the various parts to each other
        return drawLabel(label,MAX_LABEL_LENGTH)+"|"+repeat('#', value)+repeat(' ',diff)+"|";
    }

    static String drawBar(String label, double value) {
        int length = (int) Math.round(value*MAX_BAR_LENGTH);
        return drawBar(label, length);
    }
}
