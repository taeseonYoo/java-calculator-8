package calculator.controller;

import camp.nextstep.edu.missionutils.Console;

public class Input {
    private final static String INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    public String read() {
        System.out.println(INPUT_MESSAGE);
        return Console.readLine();
    }
}
