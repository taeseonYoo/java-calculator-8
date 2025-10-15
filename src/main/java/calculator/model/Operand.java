package calculator.model;

public class Operand {
    private int operand;

    public Operand(int operand) {
        setOperand(operand);
    }

    private void setOperand(int operand) {
        if (operand <= 0) {
            throw new IllegalArgumentException("피연산자는 양수만 입력 가능합니다.");
        }
        this.operand = operand;
    }

    public int getOperand() {
        return this.operand;
    }
}
