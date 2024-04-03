package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGame {
    private static final int LOTTO_PRICE = 1_000;
    private static final List<Integer> CANDIDATE_NUMBERS = IntStream.range(Lotto.MIN_LOTTO_NUMBER, Lotto.MAX_LOTTO_NUMBER + 1).boxed().collect(Collectors.toList());

    private final List<Lotto> lottos;
    public LottoGame(int budget, NumberGenerator numberGenerator) {
        if (budget < LOTTO_PRICE) {
            throw new RuntimeException("적어도 로또 1개를 살 만큼의 돈은 가져오셔야 합니다 ^^7");
        }
        int numToBuy = budget / LOTTO_PRICE;
        this.lottos = IntStream
                .range(0, numToBuy)
                .mapToObj(i -> new Lotto(numberGenerator.generateNumbers(CANDIDATE_NUMBERS, Lotto.LOTTO_NUMBERS_LENGTH)))
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public GameResult matchWith(WinningNumber winningNumber) {
        List<LottoResult> results = matchResult(winningNumber);
        double profitRate = calculateProfitRate(results);
        return new GameResult(results, profitRate);
    }

    private List<LottoResult> matchResult(WinningNumber winningNumber) {
        return winningNumber.match(lottos);
    }

    private double calculateProfitRate(List<LottoResult> results) {
        int totalPrize = results.stream().mapToInt(LottoResult::getPrize).sum();
        int numBuy = results.size();
        int totalPay = numBuy * LOTTO_PRICE;
        return (double) totalPrize / totalPay;
    }
}
