package com.github.liaojiacan.geo;


import java.util.List;

public interface PeopleNearByService {
	/**
	 * 记录用户的坐标
	 * @param username
	 * @param x 经度
	 * @param y 维度
	 */
	void recordCoord(String username,Double x,Double y);

	/**
	 * 计算某个坐标 附近某个距离内的人
	 * @param x 经度
	 * @param y 维度
	 * @param distance
	 * @return
	 */
	List<PeopleNearByDto> findNearBy(Double x,Double y,Double distance);

}
