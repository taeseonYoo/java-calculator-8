package calculator.model;

import java.util.Arrays;

public class IntegerCalculator implements Calculator {
    private final Operand[] operands;

    public IntegerCalculator(String inputValue) {
        if (verifyInputValueIsEmpty(inputValue)) {
            operands = new Operand[1];
            operands[0] = new Operand(0);
        } else {
            Separator separator = new Separator(inputValue);
            operands = separator.split(inputValue);
            verifyOperandIsPositive();
        }
    }

    private boolean verifyInputValueIsEmpty(String inputValue) {
        return inputValue.isEmpty();
    }

    private void verifyOperandIsPositive() {
        for (Operand operand : operands) {
            if (operand.getOperand() <= 0) {
                throw new IllegalArgumentException("피연산자는 양수만 입력 가능합니다.");
            }
        }
    }

    @Override
    public int plus() {
        return Arrays.stream(operands).mapToInt(Operand::getOperand).sum();
    }
}
