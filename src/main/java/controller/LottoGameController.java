package controller;

import lotto.*;
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
        List<Lotto> manualLottos = LottoGameInputView.getManualLottos();
        LottoGame lottoGame = new LottoGame(budget, new NumberGenerator(), manualLottos);
        displayPurchasedLottos(lottoGame);
        return lottoGame;
    }

    private static void displayPurchasedLottos(LottoGame lottoGame) {
        LottoGameOutputView.displayNumberOfLottos(lottoGame.getManualLottoSize(), lottoGame.getAutoLottoSize());
        List<Lotto> lottos = lottoGame.getLottos();
        lottos.stream()
                .map(Lotto::getLottoNumbers)
                .forEach(LottoGameOutputView::displayLotto);
    }

    private static GameResult matchWinningNumber(LottoGame lottoGame) {
        WinningLotto winningLotto = LottoGameInputView.getWinningNumber();
        return lottoGame.matchWith(winningLotto);
    }

    private static void displayResult(GameResult gameResult) {
        LottoGameOutputView.displayResultHeader();
        LottoGameOutputView.displayResultLotto(gameResult);
        LottoGameOutputView.displayResultProfitRate(gameResult);
    }
}
