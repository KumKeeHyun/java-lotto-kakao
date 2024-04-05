package lotto;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoResult {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    BLANK(-1, false, 0);

    private final int matchCount;
    private final boolean bonus;
    private final int prize;

    LottoResult(int matchCount, boolean bonus, int prize) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.prize = prize;
    }

    private boolean match(int winCount, boolean hasBonus) {
        if (this.matchCount != winCount) {
            return false;
        }
        if (this.matchCount == 5) {
            return this.bonus == hasBonus;
        }
        return true;
    }

    public static LottoResult getResult(int winCount, boolean hasBonus) {
        return Arrays.stream(LottoResult.values())
                .filter(lottoResult -> lottoResult.match(winCount, hasBonus))
                .findFirst()
                .orElse(LottoResult.BLANK);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isNotBlank() {
        return this != BLANK;
    }

    public static Comparator<LottoResult> comparator() {
        return Comparator.comparing(LottoResult::getPrize);
    }

}
