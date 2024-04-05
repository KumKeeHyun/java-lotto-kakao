package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto {

    private final Lotto lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> lottoNumbers, int bonusNumber) {
        this(new Lotto(lottoNumbers), LottoNumber.of(bonusNumber));
    }

    public WinningLotto(Lotto lottoNumbers, LottoNumber bonusNumber) {
        validateDuplicated(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicated(Lotto lottoNumbers, LottoNumber bonusNumber) {
        boolean duplicated = lottoNumbers.contains(bonusNumber);
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
        int matchCount = lotto.match(lottoNumbers);
        boolean hasBonus = lotto.contains(bonusNumber);
        return LottoResult.getResult(matchCount, hasBonus);
    }

}
