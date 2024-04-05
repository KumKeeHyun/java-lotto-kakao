package lotto;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    private WinningLotto winningLotto;

    @BeforeEach
    void setup() {
        winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
    }

    @Test
    void invalidWinningNumberTest() {
        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 1))
                .isInstanceOf(RuntimeException.class);

        assertThatThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void matchLotto6MatchTest() {
        LottoResult result = winningLotto.match(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        assertThat(result).isEqualTo(LottoResult.FIRST);
    }

    @Test
    void matchLotto5MatchAndBonusTest() {
        LottoResult result = winningLotto.match(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        assertThat(result).isEqualTo(LottoResult.SECOND);
    }

    @Test
    void matchLotto5MatchTest() {
        LottoResult result = winningLotto.match(new Lotto(List.of(1, 2, 3, 4, 5, 16)));
        assertThat(result).isEqualTo(LottoResult.THIRD);
    }

    @Test
    void matchLotto4MatchTest() {
        LottoResult result = winningLotto.match(new Lotto(List.of(1, 2, 3, 4, 15, 16)));
        assertThat(result).isEqualTo(LottoResult.FOURTH);
    }

    @Test
    void matchLotto3MatchTest() {
        LottoResult result = winningLotto.match(new Lotto(List.of(1, 2, 3, 14, 15, 16)));
        assertThat(result).isEqualTo(LottoResult.FIFTH);
    }

    @Test
    void matchLottoBlankTest() {
        LottoResult result = winningLotto.match(new Lotto(List.of(11, 12, 13, 14, 15, 16)));
        assertThat(result).isEqualTo(LottoResult.BLANK);
    }
}
