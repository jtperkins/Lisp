package Driver;

import Lisp.lispEvaluator;
import java.util.Scanner;
import java.util.Stack;

public class driver {


    public static void main(String[] args) {
        String input;
        Scanner scan = new Scanner(System.in);
        lispEvaluator eval = new lispEvaluator();

        while (true) {
            System.out.println("Enter a lisp arithmetic expression: ");
            input = scan.nextLine();
            eval.evaluateString(input);
            }
    }
}

