package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {
    @ParameterizedTest
    @CsvSource({"6,false,FIRST", "5,true,SECOND", "5,false,THIRD", "4,false,FOURTH", "3,false,FIFTH", "2,false,NONE"})
    void 일치개수와_보너스여부에_따라_등수를_반환한다(int count, boolean bonus, Rank expected) {
        assertThat(Rank.of(count, bonus)).isEqualTo(expected);
    }

    @Test
    void Rank_등수에_따라_상금이_정상적으로_반환된다() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000);
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000);
    }
}
