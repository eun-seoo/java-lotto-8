package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {
    private static final List<Rank> PRINT_ORDER = List.of(
            Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST
    );

    public static void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printResult(LottoResult result, int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---");

        for (Rank rank : PRINT_ORDER) {
            printRankResult(rank, result);
        }

        printProfitRate(result, purchaseAmount);
    }

    private static void printRankResult(Rank rank, LottoResult result) {
        String bonusMessage = rank.hasBonus() ? ", 보너스 볼 일치" : "";
        System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                rank.getMatchCount(),
                bonusMessage,
                rank.getPrize(),
                result.getCountByRank(rank));
    }

    private static void printProfitRate(LottoResult result, int purchaseAmount) {
        double profitRate = result.calculateProfitRate(purchaseAmount);
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}
