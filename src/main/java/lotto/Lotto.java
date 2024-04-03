package lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    public static final int LOTTO_NUMBERS_LENGTH = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLength(lottoNumbers);
        validateOutBounded(lottoNumbers);
        validateDuplicated(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }


    private void validateLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_LENGTH) {
            throw new RuntimeException("숫자는 6개여야 합니다");
        }
    }
    private void validateOutBounded(List<Integer> numbers) {
        boolean outBounded = numbers.stream().anyMatch(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER);
        if (outBounded) {
            throw new RuntimeException("숫자는 1부터 45까지만 가능합니다");
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

    public boolean contains(Integer number) {
        return getLottoNumbers().contains(number);
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}
