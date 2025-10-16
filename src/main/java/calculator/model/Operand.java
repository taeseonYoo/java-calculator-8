package calculator.model;

public class Operand {
    private int operand;

    public Operand(int operand) {
        setOperand(operand);
    }

    private void setOperand(int operand) {
        this.operand = operand;
    }

    public int getOperand() {
        return this.operand;
    }
}
