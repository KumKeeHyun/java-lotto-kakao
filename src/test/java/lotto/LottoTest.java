package lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void matchWithWinningNumberTest() {
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(winningNumber);
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.SIX_MATCH);
    }

    @Test
    void matchWithFiveMatch() {
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.FIVE_AND_BONUS_MATCH);
    }

    @Test
    void matchLooser() {
        List<Integer> winningNumber = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumber lottoNumber = new WinningNumber(
                winningNumber, bonusNumber
        );

        Lotto lotto = new Lotto(List.of(45, 44, 43, 42, 41, 40));
        LottoResult lottoResult = lotto.matchNumber(lottoNumber);
        assertThat(lottoResult).isEqualTo(LottoResult.BLANK_MATCH);

    }
}
