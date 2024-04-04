package lotto;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameResult {

    private final Map<LottoResult, Long> resultsMap;
    private final double profitRate;

    public GameResult(List<LottoResult> results, double profitRate) {
        this.resultsMap = results.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        this.profitRate = profitRate;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public int getResultCount(LottoResult lottoResult) {
        return resultsMap.getOrDefault(lottoResult, 0L).intValue();
    }
}
