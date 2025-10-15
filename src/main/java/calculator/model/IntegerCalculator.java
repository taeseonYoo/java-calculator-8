package calculator.model;

import java.util.Arrays;

public class IntegerCalculator implements Calculator {
    private final Operand[] operands;

    public IntegerCalculator(String inputValue) {
        //TODO Separator로 operands를 초기화한다.
        Separator separator = new Separator(inputValue);
        operands = separator.split(inputValue);
    }

    @Override
    public int plus() {
        return Arrays.stream(operands).mapToInt(Operand::getOperand).sum();
    }
}
