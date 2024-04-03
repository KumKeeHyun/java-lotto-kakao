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
        LottoGame lottoGame = new LottoGame(budget, new NumberGenerator());
        displayPurchasedLottos(lottoGame);
        return lottoGame;
    }

    private static void displayPurchasedLottos(LottoGame lottoGame) {
        List<Lotto> lottos = lottoGame.getLottos();
        LottoGameOutputView.displayNumberOfLottos(lottos.size());
        lottos.stream()
                .map(Lotto::getLottoNumbers)
                .forEach(LottoGameOutputView::displayLotto);
    }

    private static GameResult matchWinningNumber(LottoGame lottoGame) {
        WinningNumber winningNumber = LottoGameInputView.getWinningNumber();
        return lottoGame.getGameResult(winningNumber);
    }

    private static void displayResult(GameResult gameResult) {
        LottoGameOutputView.displayResultHeader();
        LottoGameOutputView.displayResultLotto(gameResult);
        LottoGameOutputView.displayResultProfitRate(gameResult);
    }
}
