package calculator.controller;

import calculator.model.Calculator;
import calculator.model.IntegerCalculator;
import calculator.view.Output;

public class CalculatorController {
    public static void work() {
        Input input = new Input();
        String inputValue = input.read();

        Calculator calculator = new IntegerCalculator(inputValue);
        int result = calculator.plus();

        Output output = new Output();
        output.write(result);
    }
}
