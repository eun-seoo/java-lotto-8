package lotto.domain;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static lotto.domain.WinningLottoTest.*;

public class LottoResultTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = new WinningLotto(new Lotto(WINNING_NUMBERS), BONUS_NUMBER);
    }

    @Test
    void 각_등수별_당첨_개수를_집계한다() {
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 8, 9))
        );

        LottoResult result = new LottoResult(userLottos, winningLotto);

        assertThat(result.getCountByRank(Rank.FIRST)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.SECOND)).isEqualTo(1);
        assertThat(result.getCountByRank(Rank.FOURTH)).isEqualTo(1);

    }

    @Test
    void 총_수익률을_계산한다() {
        List<Lotto> userLottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7))
        );
        int purchaseAmount = 2000;

        LottoResult result = new LottoResult(userLottos, winningLotto);
        double profitRate = result.calculateProfitRate(purchaseAmount);

        assertThat(profitRate).isEqualTo(101_500_000.0);
    }

}
