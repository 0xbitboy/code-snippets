package com.github.liaojiacan.lottery;

public class LotteryPrize {
	private String id;
	private String name;
	private double possibility;

	public LotteryPrize() {
	}


	public LotteryPrize(String id, String name, double possibility) {
		this.id = id;
		this.name = name;
		this.possibility = possibility;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPossibility() {
		return possibility;
	}

	public void setPossibility(double possibility) {
		this.possibility = possibility;
	}
}
