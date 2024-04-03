package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber {

    private final Lotto winninglotto;
    private final int bonusNumber;

    public WinningNumber(List<Integer> lottoNumbers, int bonusNumber) {
        this(new Lotto(lottoNumbers), bonusNumber);
    }

    public WinningNumber(Lotto winninglotto, int bonusNumber) {
        validateDuplicated(winninglotto, bonusNumber);
        this.winninglotto = winninglotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicated(Lotto winninglotto, int bonusNumber) {
        boolean duplicated = winninglotto.getLottoNumbers().contains(bonusNumber);
        if (duplicated) {
            throw new RuntimeException("로또 숫자와 보너스는 중복되면 안됩니다");
        }
    }

    public List<LottoResult> match(List<Lotto> lottos) {
        return lottos.stream()
                .map(this::match)
                .collect(Collectors.toList());
    }

    public LottoResult match(Lotto lotto) {
        int matchCount = lotto.match(winninglotto);
        boolean hasBonus = lotto.contains(bonusNumber);
        return LottoResult.getResult(matchCount, hasBonus);
    }

    public List<Integer> getLottoNumbers() {
        return winninglotto.getLottoNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
