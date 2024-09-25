package calculator.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SplitUtilTest {

    @ParameterizedTest
    @CsvSource(value = {
            ":0", "'':0",
            "0:0", "1:1",
            "1,2:1,2", "1;2:1,2",
            "1,2,3:1,2,3", "1;2,3:1,2,3", "1,2;3:1,2,3", "1;2;3:1,2,3",
    }, delimiter = ':')
    @DisplayName("쉼표(,), 세미콜론(;) 을 포함한 문자열이 오면 숫자 배열로 분리한다.")
    void commaOrSemiColonSplit(String input, String results) {
        Integer[] actual = SplitUtil.integers(input);
        Integer[] expected = expectedResults(results);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("//와 \n 사이 문자 를 포함한 문자열이 오면 숫자 배열로 분리한다.")
    void customSplit() {
        Integer[] actual = SplitUtil.integers("//_\n1_2_3");
        Integer[] expected = new Integer[]{1, 2, 3};

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "a,1,2", "a;1,2", "a,1;2", "a;1;2",
            "-1,1,2", "-1;1,2", "-1,1;2", "-1;1;2",
    })
    @DisplayName("숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw 한다.")
    void negativeNumberOrNotNumberSplitError(String input) {
        assertThatThrownBy(() -> SplitUtil.integers(input))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("입력할 수 없습니다.");
    }

    private static Integer[] expectedResults(String results) {
        return Arrays.stream(results.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }
}
