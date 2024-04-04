package lotto;

public class LottoNumber {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new RuntimeException("로또 숫자는 1부터 45까지만 가능합니다");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
