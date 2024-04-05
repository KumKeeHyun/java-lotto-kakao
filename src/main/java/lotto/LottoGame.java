package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoGame {

    private static final List<Integer> CANDIDATE_NUMBERS = IntStream
            .rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toUnmodifiableList());

    private final List<Lotto> manualLottos;
    private final List<Lotto> autoLottos;

    public LottoGame(int budget, NumberGenerator numberGenerator) {
        this(budget, numberGenerator, List.of());
    }

    public LottoGame(int budget, NumberGenerator numberGenerator, List<Lotto> manualLottos) {
        int numToAutoBuy = LottoPrice.canBuy(budget, manualLottos);
        this.autoLottos = IntStream.range(0, numToAutoBuy)
                .mapToObj(i -> new Lotto(generateLottoNumbers(numberGenerator)))
                .collect(Collectors.toList());
        this.manualLottos = manualLottos;
    }

    private List<Integer> generateLottoNumbers(NumberGenerator numberGenerator) {
        return numberGenerator.generateNumbers(CANDIDATE_NUMBERS, Lotto.LOTTO_NUMBERS_LENGTH);
    }

    public List<Lotto> getLottos() {
        return Stream.concat(manualLottos.stream(), autoLottos.stream())
                .collect(Collectors.toUnmodifiableList());
    }

    public int getAutoLottoSize() {
        return autoLottos.size();
    }

    public int getManualLottoSize() {
        return manualLottos.size();
    }

    public GameResult matchWith(WinningLotto winningLotto) {
        List<LottoResult> results = matchResult(winningLotto);
        double profitRate = calculateProfitRate(results);
        return new GameResult(results, profitRate);
    }

    private List<LottoResult> matchResult(WinningLotto winningLotto) {
        return winningLotto.match(getLottos());
    }

    private double calculateProfitRate(List<LottoResult> results) {
        int totalPrize = results.stream().mapToInt(LottoResult::getPrize).sum();
        int numBuy = results.size();
        int totalPay = LottoPrice.totalPay(numBuy);
        return (double) totalPrize / totalPay;
    }
}
