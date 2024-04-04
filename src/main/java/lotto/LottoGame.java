package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {

    private static final List<Integer> CANDIDATE_NUMBERS = IntStream
            .range(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER + 1)
            .boxed()
            .collect(Collectors.toList());

    private final List<Lotto> lottos;

    public LottoGame(int budget, NumberGenerator numberGenerator) {
        int numToBuy = LottoPrice.canBuy(budget);
        this.lottos = IntStream
                .range(0, numToBuy)
                .mapToObj(i -> new Lotto(numberGenerator.generateNumbers(CANDIDATE_NUMBERS, Lotto.LOTTO_NUMBERS_LENGTH)))
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public GameResult matchWith(WinningLotto winningLotto) {
        List<LottoResult> results = matchResult(winningLotto);
        double profitRate = calculateProfitRate(results);
        return new GameResult(results, profitRate);
    }

    private List<LottoResult> matchResult(WinningLotto winningLotto) {
        return winningLotto.match(lottos);
    }

    private double calculateProfitRate(List<LottoResult> results) {
        int totalPrize = results.stream().mapToInt(LottoResult::getPrize).sum();
        int numBuy = results.size();
        int totalPay = LottoPrice.totalPay(numBuy);
        return (double) totalPrize / totalPay;
    }
}
