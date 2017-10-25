package com.github.liaojiacan.geo;

public class PeopleNearByDto {

	private String name;
	private String distance;


	public PeopleNearByDto(String name, String distance) {
		this.name = name;
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
}
