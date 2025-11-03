package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
    private static final int PRICE = 1000;

    public List<Lotto> buyLottos(int purchaseAmount) {
        validateAmount(purchaseAmount);
        int count = purchaseAmount / PRICE;

        List<Lotto> lottos = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream()
                    .sorted()
                    .collect(Collectors.toList());

            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    private void validateAmount(int amount) {
        if(amount % PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
