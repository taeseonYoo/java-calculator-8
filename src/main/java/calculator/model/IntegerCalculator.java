package calculator.model;

public class IntegerCalculator implements Calculator {
    @Override
    public int plus(Operand op1, Operand op2) {
        return op1.getOperand() + op2.getOperand();
    }
}
