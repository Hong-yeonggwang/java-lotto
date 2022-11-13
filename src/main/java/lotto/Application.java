package lotto;

import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    public static int inputpurchase(){
        System.out.println("구입금액을 입력해 주세요.");
        String userInput = Console.readLine();
        int returnData = Integer.valueOf(userInput);
        return returnData;
    }
    //사용자가 로또는 몇개를 구매했는지 확인하는 메서드
    public static int countBuyGame(int buyMoney){
        int returnData = buyMoney / 1000;
        int remainder = buyMoney % 1000;
        if(remainder != 0){
            throw new IllegalArgumentException("[ERROR] 구매금액은 1000원 단위로 입력가능합니다.");
        }
        return returnData;
    }
    public static Lotto[] buyLotto (int buyCnt){
        Lotto[] returnData = new Lotto[buyCnt];
        for(int i = 0; i < buyCnt; i++){
            List<Integer> lottoNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            returnData[i] = new Lotto(lottoNumber);
        }
        return returnData;
    }
    public static void calProfitRateRun(Lotto[] lottos){
        LottoWin lottoWin = LottoWin.getInstance();
        for(Lotto lotto : lottos) {
            int[] sameCnt = lotto.checkWin();
            int[] expenseWithProfit = lotto.expenseWithProfit(sameCnt);
            lottoWin.addExpenseWithprofit(expenseWithProfit);
        }
    }
    public static void printLottoNum(Lotto[] lottos){
        System.out.println(lottos.length+"개를 구매했습니다.");
        for(Lotto lotto : lottos){
            List<Integer> lottoNums = lotto.getNumbers();
            System.out.println(lottoNums);
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        int purchaseAmont = inputpurchase();
        int buyGameCnt = countBuyGame(purchaseAmont);
        Lotto[] buyGames = buyLotto(buyGameCnt);
        printLottoNum(buyGames);
        calProfitRateRun(buyGames);
        LottoWin lottoWin = LottoWin.getInstance();
        lottoWin.profitRateCal();

    }
}
