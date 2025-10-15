package calculator.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SeparatorTest {
    @Test
    void 커스텀_구분자_테스트() {
        String inputValue = "//'\n1'2'3";
        Separator separator = new Separator(inputValue);

        Assertions.assertThat(separator.getCustomSeparator())
                .isEqualTo("'");
    }

    @Test
    void split() {
        String inputValue = "1,2,#";
        Separator separator = new Separator(inputValue);

        Assertions.assertThatThrownBy(() -> separator.split(inputValue)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void aa() {
        String inputValue = "//\t\n1\t2\t3";

        Separator separator = new Separator(inputValue);

        Assertions.assertThat(separator.getCustomSeparator()).isEqualTo("\t");
    }
}