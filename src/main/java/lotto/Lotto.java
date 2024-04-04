package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    public static final int LOTTO_NUMBERS_LENGTH = 6;
    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<Integer> numbers) {
        validateLength(numbers);
        validateDuplicated(numbers);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }


    private void validateLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_LENGTH) {
            throw new RuntimeException("숫자는 6개여야 합니다");
        }
    }

    private void validateDuplicated(List<Integer> numbers) {
        boolean duplicated = new HashSet<>(numbers).size() != numbers.size();
        if (duplicated) {
            throw new RuntimeException("숫자는 중복되면 안됩니다");
        }
    }

    public int match(Lotto anotherlotto) {
        return (int) lottoNumbers.stream()
                .filter(anotherlotto::contains)
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return getLottoNumbers()
                .contains(lottoNumber.getNumber());
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
                .mapToInt(LottoNumber::getNumber)
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }
}
