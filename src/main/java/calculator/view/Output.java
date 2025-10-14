package calculator.view;

public class Output {
    private final static String OUTPUT_MESSAGE = "결과 : ";
    public void write(int result) {
        System.out.println(OUTPUT_MESSAGE + result);
    }
}
