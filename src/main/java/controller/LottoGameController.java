package controller;

import lotto.GameResult;
import lotto.LottoGame;
import lotto.NumberGenerator;
import lotto.WinningLotto;
import view.LottoGameInputView;
import view.LottoGameOutputView;

import java.util.List;

public class LottoGameController {

    public  void startGame() {
        LottoGame lottoGame = setupLottoGame();
        GameResult gameResult = matchWinningNumber(lottoGame);
        displayResult(gameResult);
    }

    private static LottoGame setupLottoGame() {
        int budget = LottoGameInputView.getBudget();
        List<List<Integer>> manualLottos = LottoGameInputView.getManualLottos();
        LottoGame lottoGame = LottoGame.autoWithManual(budget, new NumberGenerator(), manualLottos);
        displayPurchasedLottos(lottoGame);
        return lottoGame;
    }

    private static void displayPurchasedLottos(LottoGame lottoGame) {
        LottoGameOutputView.displayNumberOfLottos(lottoGame.getManualLottoSize(), lottoGame.getAutoLottoSize());
        LottoGameOutputView.displayLottos(lottoGame.getLottos());
    }

    private static GameResult matchWinningNumber(LottoGame lottoGame) {
        LottoGameInputView.WinningLotto winningLottoInput = LottoGameInputView.getWinningNumber();
        WinningLotto winningLotto = new WinningLotto(winningLottoInput.getWinningLottoNumbers(), winningLottoInput.getWinningBonusNumber());
        return lottoGame.matchWith(winningLotto);
    }

    private static void displayResult(GameResult gameResult) {
        LottoGameOutputView.displayResultHeader();
        LottoGameOutputView.displayResultLotto(gameResult);
        LottoGameOutputView.displayResultProfitRate(gameResult);
    }
}
