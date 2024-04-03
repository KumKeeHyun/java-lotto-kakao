package lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @ParameterizedTest
    @MethodSource("generateInvalidNumbers")
    void invalidLottoTest(List<Integer> lottoNumbers) {
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(RuntimeException.class);
    }

    static Stream<Arguments> generateInvalidNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(List.of(-1, 2, 3, 4, 5, 46)),
                Arguments.of(List.of(1, 1, 3, 4, 5, 6))
        );
    }

}
