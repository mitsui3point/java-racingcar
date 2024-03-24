package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Position;

public class PositionTest {
    @Test
    @DisplayName("position")
    public void position() {
        Assertions.assertThat(new Position(4)).isEqualTo(new Position(4));
        Assertions.assertThatThrownBy(() -> {
            new Position(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
