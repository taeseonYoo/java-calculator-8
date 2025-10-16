package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("빈 문자열이 입력되면, 0을 출력해야한다.")
    void 빈_문자열_테스트() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2%3",   // 입력: "1,2" -> 결과: 3
            "1,2,3%6", // 입력: "1,2,3" -> 결과: 6
            "1,2:3%6", // 입력: "1,2:3" -> 결과: 6
    }, delimiter = '%')
    void 디폴트_구분자_사용(String inputValue, int expectedLength) {
        assertSimpleTest(() -> {
            run(inputValue);
            assertThat(output()).contains("결과 : " + expectedLength);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,0,2", "-1,0,1", "//^\\n1^0", "//^\\n1^-1"})
    void 피연산자_음수_0(String inputValue) {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException(inputValue))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 숫자만_입력_테스트() {
        assertSimpleTest(() -> {
            run("12");
            assertThat(output()).contains("결과 : " + 12);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
