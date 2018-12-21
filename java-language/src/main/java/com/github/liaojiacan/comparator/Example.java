package com.github.liaojiacan.comparator;


import javafx.util.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liaojiacan
 * @date 2018/11/29
 */

public class Example {


	/**
	 * 对存在null 值的数据进行排序
	 */
	@Test
	public void testSortListWithNullValue(){
		List<Pair<String,Double>> collection = Arrays.asList(new Pair<>("A",1D),
				new Pair<>("B",null),
				new Pair<>("C",7D),
				new Pair<>("D",5D),
				new Pair<>("B",null));
		List<Pair<String, Double>> sorted = collection.stream().sorted(Comparator.comparing(Pair::getValue,Comparator.nullsLast(Comparator.naturalOrder()))).collect(Collectors.toList());
		sorted.stream().forEach(System.out::println);
	}

}
