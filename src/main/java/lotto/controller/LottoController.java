package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;

public class LottoController {
    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        try {
            int purchaseAmount = InputView.readPurchaseAmount();
            List<Lotto> lottos = lottoMachine.buyLottos(purchaseAmount);

            outputView.printPurchaseLottos(lottos);

            List<Integer> winningNumbers = InputView.readWinningNumbers();
            int bonusNumber = InputView.readBonusNumber();

            WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNumber);
            LottoResult lottoResult = new LottoResult(lottos, winningLotto);

            outputView.printResult(lottoResult, purchaseAmount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
