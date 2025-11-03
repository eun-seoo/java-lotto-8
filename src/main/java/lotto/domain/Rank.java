package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean hasBonus;
    private final int prize;

    Rank(int matchCount, boolean hasBonus, int prize) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean hasBonus) {
        for (Rank rank : values()) {
            if (isMatched(rank, matchCount, hasBonus)) {
                return rank;
            }
        }
        return NONE;
    }

    private static boolean isMatched(Rank rank, int matchCount, boolean hasBonus) {
        if (rank.matchCount != matchCount) {
            return false;
        }
        if (matchCount == 5) {
            return rank.hasBonus == hasBonus;
        }
        return true;
    }

    public boolean hasBonus() {
        return hasBonus;
    }

    public Object getMatchCount() {
        return matchCount;
    }

    public int getPrize() {
        return prize;
    }
}
