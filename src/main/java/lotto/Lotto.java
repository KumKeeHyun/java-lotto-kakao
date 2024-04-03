package lotto;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private final List<Integer> lottoNumbers;
    public Lotto(List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public LottoResult matchNumber(WinningNumber winningNumber) {
        int winCount = (int) winningNumber.getLottoNumbers().stream().filter(lottoNumbers::contains).count();
        boolean hasBonus = lottoNumbers.contains(winningNumber.getBonusNumber());

        return LottoResult.getResult(winCount, hasBonus);
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
