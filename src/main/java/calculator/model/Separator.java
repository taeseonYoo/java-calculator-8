package calculator.model;

import java.util.Arrays;

public class Separator {
    private final static String DEFAULT_SEPARATOR = "[,:]";
    private final static String UNAVAILABLE_SEPARATOR = "[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]";
    private String customSeparator;

    public Separator(String inputValue) {
        if (verifyInputHasCustomSeparator(inputValue)) {
            verifyCustomSeparatorLength(inputValue);
            setCustomSeparator(String.valueOf(inputValue.charAt(2)));
        }
    }

    public String getCustomSeparator() {
        return this.customSeparator;
    }


    private void setCustomSeparator(String customSeparator) {
        verifyCustomSeparatorIsAvailable(customSeparator);
        this.customSeparator = customSeparator;
    }

    private void verifyCustomSeparatorLength(String inputValue) {
        if (inputValue.indexOf("\n") != 3) {
            throw new IllegalArgumentException("구분자는 한 자리만 가능합니다.");
        }
    }

    private void verifyCustomSeparatorIsAvailable(String customSeparator) {
        if (UNAVAILABLE_SEPARATOR.contains(customSeparator)) {
            throw new IllegalArgumentException("구분자는 특수 문자만 가능합니다.");
        }
    }

    private boolean verifyInputHasCustomSeparator(String inputValue) {
        return inputValue.startsWith("//") && inputValue.length() > 4 && inputValue.charAt(3) == '\n';
    }

    public Operand[] split(String inputValue) {
        try {
            if (customSeparator == null) {
                return Arrays.stream(inputValue.split(DEFAULT_SEPARATOR))
                        .map(s -> new Operand(Integer.parseInt(s)))
                        .toArray(Operand[]::new);
            } else {
                return Arrays.stream(inputValue.split(customSeparator))
                        .map(s -> new Operand(Integer.parseInt(s)))
                        .toArray(Operand[]::new);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하셨습니다.");
        }
    }
}
