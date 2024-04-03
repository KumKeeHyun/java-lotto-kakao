package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameTest {

    @Test
    void buyLottoTest() {
        int budget = 5_000;
        LottoGame game = new LottoGame(budget, new NumberGenerator());
        assertThat(game.getLottos().size()).isEqualTo(5);
    }

    @Test
    void calculateResult() {
        int budget = 2_000;
        NumberGenerator fakeGenerator = new FakeNumberGenerator(List.of(
                List.of(10, 20, 30, 1, 2, 3),
                List.of(7, 8, 9, 10, 11, 12)
        ));
        LottoGame game = new LottoGame(budget, fakeGenerator);
        WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 7);
        GameResult results = game.matchWith(winningNumber);

        assertThat(results.getResultCount(LottoResult.THREE_MATCH)).isEqualTo(1);
        assertThat(results.getProfitRate()).isEqualTo(2.5);
    }
}
