package calculator.model;

import java.util.Arrays;

public class Separator {
    private final static String DEFAULT_SEPARATOR = "[,:]";
    private String customSeparator;

    public Separator(String inputValue) {
        //TODO separator를 찾는다.
    }

    public Operand[] split(String inputValue) {
        if (customSeparator == null) {
            return Arrays.stream(inputValue.split(DEFAULT_SEPARATOR))
                    .map(s -> new Operand(Integer.parseInt(s)))
                    .toArray(Operand[]::new);
        } else {
            return Arrays.stream(inputValue.split(customSeparator))
                    .map(s -> new Operand(Integer.parseInt(s)))
                    .toArray(Operand[]::new);
        }
    }
}
