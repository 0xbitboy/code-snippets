package com.github.liaojiacan.lottery;

import java.util.List;
import java.util.Random;

public class LotteryMachine {

	private PossibilityArea[] table;
	private int bound = 1;
	private Random random = new Random();

	private static final int BASE_WEIGHT = 10000;

	class PossibilityArea{
		private LotteryPrize prize;
		private double start;
		private double end;
		public PossibilityArea(LotteryPrize prize, double start, double end) {
			this.prize = prize;
			this.start = start;
			this.end = end;
			System.out.printf("%s:[%f,%f)\n",prize.getName(),start,end);
		}
	}

	public LotteryMachine(List<LotteryPrize> prizes) {
		table = new PossibilityArea[prizes.size()];
		double start = 0;
		for(int i=0;i<prizes.size();i++){
			LotteryPrize prize = prizes.get(i);
			double weight = prize.getPossibility() * BASE_WEIGHT;
			Double end = start + weight;
			PossibilityArea area = new PossibilityArea(prize,start,end);
			table[i]= area;
			bound = end.intValue();
			start=end;
		}
	}


	private LotteryPrize binarySearch(int rnd){
		int low = 0;
		int hight = table.length;
		while (low<hight){
			int mid = (low + hight) / 2;
			PossibilityArea area = table[mid];
			if(area.start<=rnd && area.end>rnd){
				return  area.prize;
			}
			if(area.end<=rnd){
				low=mid+1;
			}
			if(area.start>rnd){
				hight=mid;
			}
		}
		return null;

	}

	public LotteryPrize go(){
		int rnd = random.nextInt(bound);
		return binarySearch(rnd);
	}
}
