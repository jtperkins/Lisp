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

    public String evaluateString(String expression) throws Exception {
        int count = expression.length();
        int index = 0;
        int lCount = 0;
        int rCount = 0;
        Character temp;
        Character tempOperator;
        Double tempDouble;

        if (!isValid(expression))
            throw new Exception();

        while(index < count) {
           temp = expression.charAt(index);
           if (temp.equals('(')) {
               expressionStack.push(expression.charAt(++index));
               lCount++;
               index++;
           }

           if (temp.equals('-')) {
               StringBuilder builder = new StringBuilder();
               builder.append(temp);
               builder.append(expression.charAt(++index));
               //System.out.println(builder);
               expressionStack.push(Double.parseDouble(builder.toString()));
               index++;
           }

           if (Character.isDigit(temp)) {
               if (Character.isDigit(expression.charAt(index + 1))) {
                   if (Character.isDigit(expression.charAt(index + 2))) {
                       StringBuilder builder = new StringBuilder();
                       builder.append(temp);
                       builder.append(expression.charAt(++index));
                       builder.append(expression.charAt(++index));
                       //System.out.println(builder);
                       expressionStack.push(Double.parseDouble(builder.toString()));
                       index++;
                   }
                   else {
                       StringBuilder builder = new StringBuilder();
                       builder.append(temp);
                       builder.append(expression.charAt(++index));
                       //System.out.println(builder);
                       expressionStack.push(Double.parseDouble(builder.toString()));
                       index++;
                   }

               }
               else {
                   expressionStack.push(temp);
                   index++;
               }

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

        return String.valueOf(expressionStack.pop());
    }

    private boolean isValid(String expression) {
        int lCount = 0;
        int rCount = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                if (expression.charAt(i + 1) == '+' || expression.charAt(i + 1) == '-' || expression.charAt(i + 1) == '/' || expression.charAt(i + 1) == '*')
                    lCount++;
            }

            if (expression.charAt(i) == ')')
                rCount++;
        }
        if (lCount == rCount)
            return true;
        else
            return false;
    }

}
