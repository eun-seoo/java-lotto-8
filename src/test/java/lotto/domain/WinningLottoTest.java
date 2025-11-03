package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {
    @Test
    void 로또_번호가_6개_모두_일치하면_1등을_반환한다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank result = winningLotto.match(userLotto);

        assertThat(result).isEqualTo(Rank.FIRST);
    }

    @Test
    void 로또_번호가_5개_일치하고_보너스_일치하면_2등을_반환한다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank result = winningLotto.match(userLotto);

        assertThat(result).isEqualTo(Rank.SECOND);
    }

    @Test
    void 로또_번호가_5개만_일치하면_3등을_반환한다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        Rank result = winningLotto.match(userLotto);

        assertThat(result).isEqualTo(Rank.THIRD);
    }

    @Test
    void 로또_번호가_4개_일치하면_4등을_반환한다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 4, 8, 9));

        Rank result = winningLotto.match(userLotto);

        assertThat(result).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 로또_번호가_3개_일치하면_5등을_반환한다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 3, 8, 9, 10));

        Rank result = winningLotto.match(userLotto);

        assertThat(result).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 로또_번호가_2개_이하면_NONE을_반환한다() {
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
        Lotto userLotto = new Lotto(List.of(1, 2, 11, 8, 9, 10));

        Rank result = winningLotto.match(userLotto);

        assertThat(result).isEqualTo(Rank.NONE);
    }

    @Test
    void 보너스_번호가_당첨번호와_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
