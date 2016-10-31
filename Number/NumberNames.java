import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberNames {
    static int[] options = {0, 0, 0, 0, 0, 0, 0, 0}; //{sign, millions, hundred thousands, ten thousands, thousands, hundreds, tens, ones}
    static String output = "";

    public static void main(String[] args) {
        System.out.print("Please enter a integer between negative and positive one million (inclusive): ");
        Scanner sc = new Scanner(System.in);
        int input = 0;
        try {
            input = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: input must be an integer from -1,000,000 and 1,000,000");
        }

        if (input == 0) {
            System.out.println("Zero");
            return;
        }

        if (input < 0) {
            options[0] = 1; //0 for positive number, 1 for negative
            input = input * -1;
        }

        int comparison = 1000000;
        int c = 1; //options index
        while (input >= 0 && comparison > 0) { //build options array
            if (input >= comparison) {
                options[c] = input / comparison;
                input = input % (options[c] * comparison);
            }
            comparison /= 10;
            c++;
        }
        System.out.println(Arrays.toString(options));
        buildString(options);
        System.out.println(output);
    }

    //constructs output string based on options array
    private static void buildString(int[] i) {
        String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"}; //array containing single digit names
        String[] teens = {"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"}; // array containing multiples of ten

        int c = 0;
        while (c < 8) {
            if (c == 0 && i[c] == 1) {
                output += "Negative";
            } else if (c == 1 && i[c] > 0) {
                output += ones[i[c]] + " Million,";
            } else if ((c == 2 || c == 5) && i[c] > 0) {
                output += ones[i[c]] + " Hundred";
            } else if ((c == 3 || c == 6) && i[c] > 0) {
                if (i[c] == 1 && i[c + 1] > 0) {
                    output += teens[i[c + 1]] + " Thousand,";
                    c++;
                } else {
                    output += tens[i[c]] + " ";
                }
            } else if (c == 4) {
                output += ones[i[c]] + "Thousand,";
            } else if (c == 7 && i[c] > 0) {
                output += ones[i[c]];
            }
            space();
            c++;
        }
    }

    //inserts spaces if needed
    private static void space() {
        if (!output.isEmpty() && output.charAt(output.length() - 1) != ' ') {
            output += " ";
        }
    }
}
