package lotto.domain;

import java.util.List;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto userLotto) {
        int countMatch = countMatch(userLotto);
        boolean bonusMatch = isBonusMatch(userLotto);
        return Rank.of(countMatch, bonusMatch);
    }

    private int countMatch(Lotto userLotto) {
        List<Integer> userNumbers = userLotto.getNumbers();

        int count = 0;

        for (int number : userNumbers) {
            if (winningNumbers.getNumbers().contains(number)) {
                count++;
            }
        }
        return count;
    }

    private boolean isBonusMatch(Lotto userLotto) {
        return userLotto.getNumbers().contains(bonusNumber);
    }
}
