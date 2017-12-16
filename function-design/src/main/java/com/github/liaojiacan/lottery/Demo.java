package com.github.liaojiacan.lottery;

import java.util.Arrays;
import java.util.List;

public class Demo {

	/**
	 一等奖:[0.000000,100.000000)
	 二等奖:[100.000000,1100.000000)
	 三等奖:[1100.000000,2600.000000)
	 四等奖:[2600.000000,4600.000000)
	 五等奖:[4600.000000,7100.000000)
	 六等奖:[7100.000000,10000.000000)
	 * @param args
	 */

	public static void main(String[] args) {

		LotteryPrize prizeA = new LotteryPrize("A","一等奖",0.01);
		LotteryPrize prizeB = new LotteryPrize("B","二等奖",0.1);
		LotteryPrize prizeC = new LotteryPrize("C","三等奖",0.15);
		LotteryPrize prizeD = new LotteryPrize("D","四等奖",0.2);
		LotteryPrize prizeE = new LotteryPrize("E","五等奖",0.25);
		LotteryPrize prizeF = new LotteryPrize("F","六等奖",0.29);
		List<LotteryPrize> prizes = Arrays.asList(prizeA, prizeB, prizeC, prizeD, prizeE, prizeF);
		LotteryMachine machine = new LotteryMachine(prizes);

		for(int i=0;i<100;i++){
			System.out.printf("恭喜获得 %s\n",machine.go().getName());
		}
	}
}
