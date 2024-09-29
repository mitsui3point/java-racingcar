package racingcar.model.wrapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.ForwardNumberException;
import racingcar.model.Forward;

import static org.assertj.core.api.Assertions.*;

public class ForwardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void number_값은_0_9_사이이다(int number) {
        assertThatNoException().isThrownBy(() -> {
            new ForwardNumber(number);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 10, Integer.MAX_VALUE})
    void number_값은_0미만_9초과시_에러(int number) {
        assertThatThrownBy(() -> {
            new ForwardNumber(number);
        }).isInstanceOf(ForwardNumberException.class).hasMessage("0~9 사이의 숫자 외에는 전진숫자로 허용되지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void 동등_비교(int number) {
        ForwardNumber actual = new ForwardNumber(number);
        ForwardNumber expected = new ForwardNumber(number);
        assertThat(actual).isEqualTo(expected);
    }
}
