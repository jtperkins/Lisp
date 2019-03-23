package Lisp;

/**
 This class represents either an operand or an operator
 for an arithmetic expression in Lisp.
 */
public class lispToken
{
    private Character operator;
    private Double    operand;
    private boolean   isOperator;

    /** Constructors for objects of class lispToken. */
    public lispToken(Character anOperator)
    {
        operator = anOperator;
        isOperator = true;
        operand = 0.0;
    } // end constructor

    public lispToken(Double value)
    {
        operand = value;
        isOperator = false;
        operator = ' ';
    } // end constructor

    /** Applies this operator to two given operand values.
     @param value1  The value of the first operand.
     @param value2  The value of the second operand.
     @return  The real result of the operation. */
    public Double applyOperator(Double value1, Double value2)
    {
        Double result = 0.0;

        switch (operator)
        {
            case '+':
                result = value1 + value2;
                //System.out.println("Computed " + value1 + " + " + value2 + " = " + result);
                break;
            case '-':
                result = value1 - value2;
                //System.out.println("Computed " + value1 + " - " + value2 + " = " + result);
                break;
            case '*':
                result = value1 * value2;
                //System.out.println("Computed " + value1 + " * " + value2 + " = " + result);
                break;
            case '/':
                result = value1 / value2;
                //System.out.println("Computed " + value1 + " / " + value2 + " = " + result);
                break;
        } // end switch

        return result;
    } // end applyOperator

    /** Gets the identity value of this operator.
     For example, x + 0 = x,  so 0 is the identity for +
     and will be the value associated with the expression (+).
     @return  The identity value of the operator. */
    public Double getIdentity()
    {
        Double result = 0.0;

        switch (operator)
        {
            case '+':
                result = 0.0;
                break;
            case '-':
                result = 0.0;
                break;
            case '*':
                result = 1.0;
                break;
            case '/':
                result = 1.0;
                break;
        } // end switch

        return result;
    } // endGetIdentity

    /** Detects whether this operator returns a value when it has no operands.
     @return  True if the operator returns a value when it has no operands,
     or false if not. */
    public boolean takesZeroOperands()
    {
        boolean result = false;

        switch (operator)
        {
            case '+':
                result = true;
                break;
            case '-':
                result = false;
                break;
            case '*':
                result = true;
                break;
            case '/':
                result = false;
                break;
        } // end switch

        return result;
    } // end takesZeroOperands

    public Double oneOperand(Double d) {
        Double result = 0.0;
        Double operatorValue = this.getIdentity();

        switch (operator)
        {
            case '+':
                result = operatorValue + d;
                break;
            case '-':
                result = operatorValue - d;
                break;
            case '*':
                result = operatorValue * d;
                break;
            case '/':
                result = operatorValue / d;
                break;
        } // end switch
        return result;
    }

    private Double multipleOperands(Double[] arr) {
        return null;
    }

    /** Gets the value of this operand.
     @return  The real value of the operand. */
    public Double getValue()
    {
        return operand;
    } // end getValue

    /** Returns true if the object is an operator.
     @return  True is this object is an operator. */
    public boolean isOperator()
    {
        return isOperator;
    } // end isOperator

    public String toString()
    {
        String result = null;

        if (isOperator)
            result = operator.toString();
        else
            result = operand.toString();

        return result;
    } // end toString
} // end lispToken