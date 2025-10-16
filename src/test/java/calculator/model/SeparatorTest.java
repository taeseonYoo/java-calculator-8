package calculator.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SeparatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"`", "~", "!", "@", "#", "$", "%", "^", "&", "*",
            "(", ")", "_", "+", "{", "}", "[", "]", "}", "<",
            ">", "?", "/", "|", "\\", "-", "=", ";", ".", "\"", "'"})
    void customSeparator_create_success(String customSeparator) {
        //given
        String inputValue = "//" + customSeparator + "\\n";
        //when
        Separator separator = new Separator(inputValue);
        //then
        Assertions.assertThat(separator.getCustomSeparator())
                .isEqualTo(customSeparator);

    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "A", "z", "Z", "ㄱ", "가", "힣", "ㅎ", "ㅏ", "ㅣ"})
    void customSeparator_create_fail(String customSeparator) {
        //given
        String inputValue = "//" + customSeparator + "\\n";
        //when & then
        Assertions.assertThatThrownBy(() ->
                        new Separator(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = "1,2:3")
    void defaultSeparator_create_success(String inputValue) {
        //given
        Separator separator = new Separator(inputValue);
        //when & then
        Assertions.assertThat(separator.getCustomSeparator())
                .isEqualTo(null);
    }

    @Test
    void split_customSeparator_success() {
        String inputValue = "//%\\n1%2%3";
        Separator separator = new Separator(inputValue);
        Operand[] split = separator.split(inputValue);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,%2",   // 입력: "1,2" -> 길이: 2
            "1,2:3%3", // 입력: "1,2:3" -> 길이: 3
            "1:2:3%3", // 입력: "1:2:3" -> 길이: 3
            "1,2,3,,%3" // 입력: "1,2,3," -> 길이: 3
    }, delimiter = '%')
    void split_defaultSeparator_success(String inputValue, int expectedLength) {
        //given
        Separator separator = new Separator(inputValue);
        //when
        Operand[] split = separator.split(inputValue);
        //then
        Assertions.assertThat(split.length).isEqualTo(expectedLength);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1::2", "1,,2", "1,:2", ":1"})
    void split_defaultSeparator_fail(String inputValue) {
        //given
        Separator separator = new Separator(inputValue);
        //when & then
        Assertions.assertThatThrownBy(() -> separator.split(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

}