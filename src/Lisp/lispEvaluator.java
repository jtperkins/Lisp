package Lisp;


import java.util.Stack;

public class lispEvaluator {
    private Stack expressionStack;
    private Stack resultStack;
    private lispToken token;
    private Double result;

    public lispEvaluator() {
        expressionStack = new Stack();
        resultStack = new Stack();
    }

    public String evaluateString(String expression) {
        int count = expression.length();
        int index = 0;
        Character temp;

        while(index < count) {
           temp = expression.charAt(index);
           if (temp.equals('(')) {
               expressionStack.push(expression.charAt(++index));
               index++;
           }

           if (Character.isDigit(temp)) {
               expressionStack.push(temp);
               index++;
           }

           if (temp.equals(' '))
                index++;
           if (temp.equals(')')) {

               while (true) {
                   temp = (Character) expressionStack.pop();
                   if (temp.equals('+') || temp.equals('-') || temp.equals('*') || temp.equals('/'))
                       break;
                   resultStack.push(temp);
               }

               if (resultStack.size() == 1) {
                   token = new lispToken(temp);
                   Double result = token.oneOperand(Double.parseDouble(String.valueOf(resultStack.pop())));
                   expressionStack.push(result);
                   index++;
               }
               else {
                   while (!resultStack.empty()) {
                       token = new lispToken((temp));

                   }
               }

           }


        }
        return null;
    }


}
