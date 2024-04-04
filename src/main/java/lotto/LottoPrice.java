package lotto;

public class LottoPrice {

    public static final int PRICE_OF_LOTTO = 1_000;

    public static int canBuy(int budget) {
        if (budget < PRICE_OF_LOTTO) {
            throw new RuntimeException("적어도 로또 1개를 살 만큼의 돈은 가져오셔야 합니다 ^^7");
        }
        return budget / PRICE_OF_LOTTO;
    }

    public static int totalPay(int numBuy) {
        return numBuy * PRICE_OF_LOTTO;
    }

}
