package Lisp;


import java.util.LinkedList;
import java.util.Stack;

public class lispEvaluator {
    private Stack expressionStack;
    private Stack resultStack;
    private lispToken token;


    public lispEvaluator() {
        expressionStack = new Stack();
        resultStack = new Stack();
    }

    public Double evaluateString(String expression) {
        int count = expression.length();
        int index = 0;
        int lCount = 0;
        int rCount = 0;
        Character temp;
        Character tempOperator;
        Double tempDouble;

        while(index < count) {
           temp = expression.charAt(index);
           if (temp.equals('(')) {
               expressionStack.push(expression.charAt(++index));
               lCount++;
               index++;
           }

           if (Character.isDigit(temp)) {
               expressionStack.push(temp);
               index++;
           }

           if (temp.equals(' '))
                index++;
           if (temp.equals(')')) {
               rCount++;

               while (true) {
                   // System.out.println(expressionStack.peek().getClass().getName());
                   if (expressionStack.peek().getClass().getName().equalsIgnoreCase("java.lang.Double")) {
                       tempDouble = (Double) expressionStack.pop();
                       resultStack.push(tempDouble);
                   }
                   else if (expressionStack.peek().getClass().getName().equalsIgnoreCase("java.lang.Character")) {
                       temp = (Character) expressionStack.pop();
                       if (temp.equals('+') || temp.equals('-') || temp.equals('*') || temp.equals('/')) {
                           resultStack.push(temp);
                           break;
                       }

                       resultStack.push(temp);
                   }



               }
               if (resultStack.size() == 1) {
                   token = new lispToken((Character)resultStack.pop());
                   Double result = token.getIdentity();
                   expressionStack.push(result);
                   index++;
               }
               else if (resultStack.size() == 2) {
                   token = new lispToken((Character)resultStack.pop());
                   Double result = token.oneOperand(Double.parseDouble(String.valueOf(resultStack.pop())));
                   //String format = Double.toString(result);
                   expressionStack.push(result);
                   index++;
               }
               else {
                   LinkedList<Double> list = new LinkedList();
                   token = new lispToken((Character)resultStack.pop());
                   if (resultStack.empty()) {
                       expressionStack.push(token.getIdentity());
                       index++;
                   }
                   else {
                       while (!resultStack.empty()) {
                           list.add(Double.parseDouble(String.valueOf(resultStack.pop())));
                       }
                       Double result = token.multipleOperands(list);
                       //String format = Double.toString(result);
                       expressionStack.push(result);
                       index++;
                   }
               }

           }


        }
        return (Double) expressionStack.pop();
    }


}
