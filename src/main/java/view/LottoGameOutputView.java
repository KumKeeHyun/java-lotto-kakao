package view;

import lotto.GameResult;
import lotto.LottoResult;

import java.util.Arrays;
import java.util.List;

public class LottoGameOutputView {
    private static final String DISPLAY_NUMBER_OF_LOTTOS_MSG_FORMAT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String DISPLAY_RESULT_HEADER_MSG = "당첨 통계\n---------";
    private static final String DISPLAY_RESULT_LOTTO_MSG_FORMAT = "%s (%d원) - %d개";
    private static final String DISPLAY_RESULT_PROFIT_RATE_MSG = "총 수익률은 %,.2f입니다.\n";

    public static void displayNumberOfLottos(int numOfManualLottos, int numOfAutoLottos) {
        System.out.printf(DISPLAY_NUMBER_OF_LOTTOS_MSG_FORMAT, numOfManualLottos, numOfAutoLottos);
    }

    public static void displayLotto(List<Integer> lottoNumbers) {
        System.out.println(lottoNumbers);
    }

    public static void displayResultHeader() {
        System.out.println(DISPLAY_RESULT_HEADER_MSG);
    }

    public static void displayResultLotto(GameResult gameResult) {
        Arrays.stream(LottoResult.values())
                .sorted(LottoResult.comparator())
                .filter(LottoResult::isNotBlank)
                .forEach(lottoResult -> System.out.println(makeLottoResult(lottoResult, gameResult.getResultCount(lottoResult))));
    }

    private static String makeLottoResult(LottoResult lottoResult, long count) {
        return String.format(DISPLAY_RESULT_LOTTO_MSG_FORMAT, makeLottoResultName(lottoResult), lottoResult.getPrize(), count);
    }

    private static String makeLottoResultName(LottoResult lottoResult) {
        String resultName = String.format("%d개 일치", lottoResult.getMatchCount());
        if (lottoResult == LottoResult.SECOND) {
            resultName += ", 보너스 볼 일치";
        }
        return resultName;
    }

    public static void displayResultProfitRate(GameResult gameResult) {
        System.out.printf(DISPLAY_RESULT_PROFIT_RATE_MSG, gameResult.getProfitRate());
    }

}
