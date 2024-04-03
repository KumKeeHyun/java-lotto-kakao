package lotto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    private WinningNumber winningNumber;

    @BeforeEach
    void setup() {
        winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    void invalidWinningNumberTest() {
        assertThatThrownBy(() -> new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 1))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void matchLotto6MatchTest() {
        LottoResult result = winningNumber.match(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(result).isEqualTo(LottoResult.SIX_MATCH);
    }

    @Test
    void matchLotto5MatchAndBonusTest() {
        LottoResult result = winningNumber.match(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        assertThat(result).isEqualTo(LottoResult.FIVE_AND_BONUS_MATCH);
    }

    @Test
    void matchLotto5MatchTest() {
        LottoResult result = winningNumber.match(new Lotto(List.of(1, 2, 3, 4, 5, 16)));
        assertThat(result).isEqualTo(LottoResult.FIVE_MATCH);
    }

    @Test
    void matchLotto4MatchTest() {
        LottoResult result = winningNumber.match(new Lotto(List.of(1, 2, 3, 4, 15, 16)));
        assertThat(result).isEqualTo(LottoResult.FOUR_MATCH);
    }

    @Test
    void matchLotto3MatchTest() {
        LottoResult result = winningNumber.match(new Lotto(List.of(1, 2, 3, 14, 15, 16)));
        assertThat(result).isEqualTo(LottoResult.THREE_MATCH);
    }

    @Test
    void matchLottoBlankTest() {
        LottoResult result = winningNumber.match(new Lotto(List.of(11, 12, 13, 14, 15, 16)));
        assertThat(result).isEqualTo(LottoResult.BLANK_MATCH);
    }
}
