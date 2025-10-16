package calculator.model;

import java.util.Arrays;

public class Separator {
    private final static String DEFAULT_SEPARATOR = "[,:]";
    private final static String REGEX_SEPARATOR = ".^$[]-|(){}\\?*+";
    private final static String CUSTOM_SEPARATOR_LENGTH_WARN = "커스텀 구분자는 한 자리만 사용할 수 있습니다.";
    private final static String INVALID_FORMAT_WARN = "잘못된 형식을 입력하셨습니다.";
    private String customSeparator;

    public Separator(String inputValue) {
        setSeparator(inputValue);
    }

    public String getCustomSeparator() {
        return this.customSeparator;
    }

    private void setSeparator(String inputValue) {
        if (verifyInputHasCustomSeparator(inputValue)) {
            verifyCustomSeparatorLength(inputValue);
            this.customSeparator = preventRegex(inputValue.substring(2, inputValue.indexOf("\\n")));
        }
    }

    private void verifyCustomSeparatorLength(String inputValue) {
        if (inputValue.indexOf("\\n") != 3) {
            throw new IllegalArgumentException(CUSTOM_SEPARATOR_LENGTH_WARN);
        }
    }

    private String preventRegex(String customSeparator) {
        if (REGEX_SEPARATOR.contains(customSeparator)) {
            return "\\" + customSeparator;
        }
        return customSeparator;
    }


    private boolean verifyInputHasCustomSeparator(String inputValue) {
        return inputValue.startsWith("//") && inputValue.contains("\\n");
    }

    public Operand[] split(String inputValue) {
        try {
            if (customSeparator == null) {
                return Arrays.stream(inputValue.split(DEFAULT_SEPARATOR))
                        .map(s -> new Operand(Integer.parseInt(s)))
                        .toArray(Operand[]::new);
            } else {
                inputValue = deletePrefix(inputValue);
                return Arrays.stream(inputValue.split(customSeparator))
                        .map(s -> new Operand(Integer.parseInt(s)))
                        .toArray(Operand[]::new);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT_WARN);
        }
    }

    private String deletePrefix(String inputValue) {
        return inputValue.substring(inputValue.indexOf("\\n") + 2);
    }
}
