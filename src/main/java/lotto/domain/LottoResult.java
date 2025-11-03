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

    public double calculateTotalPrize() {
        double totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += (long) rank.getPrize() * getCountByRank(rank);
        }
        return totalPrize;
    }

    public double calculateProfitRate(int purchaseAmount) {
        double totalPrize = calculateTotalPrize();
        return (totalPrize / purchaseAmount) * 100;
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
}
