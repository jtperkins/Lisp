package Driver;

import Lisp.lispEvaluator;
import java.util.Scanner;
import java.util.Stack;

public class driver {


    public static void main(String[] args) {
        String input;
        Scanner scan = new Scanner(System.in);
        lispEvaluator eval = new lispEvaluator();


            System.out.println("Enter a lisp arithmetic expression: ");
            input = scan.nextLine();
            try {
                System.out.println(eval.evaluateString(input));
            } catch (Exception e){
                System.out.println(e.getMessage());
                //System.out.println("Result: Invalid expression");
            }
            while (true) {
                System.out.print("Do you want to enter another expression? (y/n): ");
                input = scan.nextLine();
                //System.out.println(input);
                if (input.equalsIgnoreCase("n")) {
                    System.out.println("Thank you for evaluating lisp expressions.");
                    break;
                }
                System.out.println("Enter a lisp arithmetic expression: ");
                input = scan.nextLine();
                try {
                    System.out.println(eval.evaluateString(input));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    //System.out.println("Result: Invalid expression");
                }
            }
    }
}

