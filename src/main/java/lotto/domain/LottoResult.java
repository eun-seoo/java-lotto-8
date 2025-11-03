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

    // 개수 조회
    public int getCountByRank(Rank rank) {
        return result.get(rank);
    }

    // 수익률 계산
    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        double profitRate = (double) totalPrize / purchaseAmount;
        return Math.round(profitRate * 100) / 100.0;
    }

    // 모든 Rank를 0으로 초기화
    private Map<Rank, Integer> initResult() {
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            map.put(rank, 0);
        }
        return map;
    }

    // 등수 집계
    private void countResults(List<Lotto> userLottos, WinningLotto winningLotto) {
        for (Lotto lotto : userLottos) {
            Rank rank = winningLotto.match(lotto);
            result.put(rank, result.get(rank) + 1);
        }
    }

    //Rank별 상금 합산
    private long calculateTotalPrize() {
        long total = 0;
        for(Rank rank : Rank.values()) {
            total += (long) rank.getPrize() * result.get(rank);
        }
        return total;
    }
}
