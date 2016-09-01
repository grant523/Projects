import java.util.InputMismatchException;
import java.util.Scanner;

public class CollatzConjecture {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter any positive integer greater than 1: ");
        int n = 0;
        try {
            n = sc.nextInt();
        }
        catch(InputMismatchException e) {
            System.out.println("Input must be an integer!");
            return;
        }
        int c = 0;
        if (n > 1) {
            while (n != 1) {
                n = nextStep(n);
                c++;
            }
            System.out.println(c + " steps to reach one.");
        }
        else {
            System.out.println("Must be greater than one!");
        }
    }
    public static int nextStep(int i) {
        if (i % 2 == 0) {
            i = i / 2;
        } else {
            i = i * 3 + 1;
        }
        return i;
    }
}
