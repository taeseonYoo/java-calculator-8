package calculator.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SeparatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"`", "~", "!", "@", "#", "%", "&", "_",
            "<", ">", "/", "=", ";", "\"", "'"})
    @DisplayName("커스텀 구분자로 특수문자를 사용하는 경우, 제대로 설정되는 지 확인한다.")
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
    @ValueSource(strings = {"$", "^", "*", "(", ")", "+", "{", "}",
            "[", "]", "}", "?", "|", "\\", "-", "."})
    @DisplayName("커스텀 구분자로 정규표현식에 사용되는 문자를 사용하는 경우, \\가 추가되어야 한다.")
    void regex_customSeparator_create_success(String customSeparator) {

        //given
        String inputValue = "//" + customSeparator + "\\n";
        //when
        Separator separator = new Separator(inputValue);
        //then
        Assertions.assertThat(separator.getCustomSeparator())
                .isEqualTo("\\" + customSeparator);
    }

    @ParameterizedTest
    @ValueSource(strings = {"//^^\\n1^^2", "//\\n12"})
    @DisplayName("커스텀 구분자는 1자리만 사용할 수 있다.")
    void customSeparator_create_fail(String inputValue) {
        //given & when & then
        Assertions.assertThatThrownBy(() -> new Separator(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("디폴트 구분자를 사용하는 경우 CustomSeparator 는 null 이어야한다.")
    void defaultSeparator_create_success() {
        //given
        Separator separator = new Separator("1,2:3");
        //when & then
        Assertions.assertThat(separator.getCustomSeparator())
                .isEqualTo(null);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1::2", "1,,2", "1,:2"})
    @DisplayName("디폴트 구분자가 두 개 붙어있는 경우에는, IllegalArgumentException 가 발생한다.")
    void split_defaultSeparator_fail_twice(String inputValue) {
        //given
        Separator separator = new Separator(inputValue);
        //when & then
        Assertions.assertThatThrownBy(() -> separator.split(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {":1", ",1"})
    @DisplayName("디폴트 구분자로 시작하는 경우에는, IllegalArgumentException 가 발생한다.")
    void split_defaultSeparator_fail_front(String inputValue) {
        //given
        Separator separator = new Separator(inputValue);
        //when & then
        Assertions.assertThatThrownBy(() -> separator.split(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"//^\\n", "//^\\n^1"})
    void split_customSeparator_fail(String inputValue) {
        //given
        Separator separator = new Separator(inputValue);
        //when & then
        Assertions.assertThatThrownBy(() -> separator.split(inputValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}