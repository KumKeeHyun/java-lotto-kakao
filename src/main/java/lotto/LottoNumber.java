package lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .forEach(lottoNum -> lottoNumbers.put(lottoNum.getNumber(), lottoNum));
    }

    private final int number;

    private LottoNumber(int number) {
        validateNumberBound(number);
        this.number = number;
    }

    private static void validateNumberBound(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new RuntimeException("로또 숫자는 1부터 45까지만 가능합니다");
        }
    }

    public static LottoNumber of(int number) {
        validateNumberBound(number);
        return lottoNumbers.get(number);
    }

    public int getNumber() {
        return number;
    }
}
