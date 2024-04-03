package lotto;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoResult {
    SIX_MATCH(6, false, 2_000_000_000),
    FIVE_AND_BONUS_MATCH(5, true, 30_000_000),
    FIVE_MATCH(5, false, 1_500_000),
    FOUR_MATCH(4, false, 50_000),
    THREE_MATCH(3, false, 5_000),
    BLANK_MATCH(-1, false, 0);

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
                .orElse(LottoResult.BLANK_MATCH);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isNotBlank() {
        return this != BLANK_MATCH;
    }

    public static Comparator<LottoResult> comparator() {
        return Comparator.comparing(LottoResult::getMatchCount).thenComparingInt(result -> result.bonus ? 0 : 1);
    }

}
