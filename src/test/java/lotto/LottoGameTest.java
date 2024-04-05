package lotto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoGameTest {

    @Test
    void buyLottoTest() {
        int budget = 5_000;
        LottoGame game = new LottoGame(budget, new NumberGenerator());
        assertThat(game.getLottos().size()).isEqualTo(5);
    }

    @Test
    void buyManualLottoTest() {
        int budget = 5_000;
        List<Lotto> manualLottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        LottoGame game = new LottoGame(budget, new NumberGenerator(), manualLottos);

        assertThat(game.getManualLottoSize()).isEqualTo(2);
        assertThat(game.getAutoLottoSize()).isEqualTo(3);
    }

    @Test
    void invalidBuyLottoTest() {
        int budget = 1_000;
        List<Lotto> manualLottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        assertThatThrownBy(() -> new LottoGame(budget, new NumberGenerator(), manualLottos))
                .isInstanceOf(RuntimeException.class);

    }

    @Test
    void calculateResult() {
        int budget = 2_000;
        NumberGenerator fakeGenerator = new FakeNumberGenerator(List.of(
                List.of(10, 20, 30, 1, 2, 3),
                List.of(7, 8, 9, 10, 11, 12)
        ));
        LottoGame game = new LottoGame(budget, fakeGenerator);
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        GameResult results = game.matchWith(winningLotto);

        assertThat(results.getResultCount(LottoResult.FIFTH)).isEqualTo(1);
        assertThat(results.getProfitRate()).isEqualTo(2.5);
    }
}
