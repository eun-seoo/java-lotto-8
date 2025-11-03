package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result;

    public LottoResult(List<Lotto> userLottos, WinningLotto winningLotto) {
        this.result = initResult();
        countResults(userLottos, winningLotto);
    }

    public int getCountByRank(Rank rank) {
        return result.get(rank);
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        double profitRate = (double) totalPrize / purchaseAmount;
        return Math.round(profitRate * 1000) / 10.0;
    }

    private Map<Rank, Integer> initResult() {
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }
        return map;
    }

    private void countResults(List<Lotto> userLottos, WinningLotto winningLotto) {
        for (Lotto lotto : userLottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
    }

    private long calculateTotalPrize() {
        long total = 0;
        for (Rank rank : Rank.values()) {
            total += (long) rank.getPrize() * result.get(rank);
        }
        return total;
    }
}
