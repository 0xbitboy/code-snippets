package com.github.liaojiacan.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaojiacan
 * @date 2018/11/29
 */
public class Example {

	/**
	 * ParallelStream 并行处理版本的Stream
	 * 下面得到的顺序不是有序的
	 */
	@Test
	public void testParallelStream(){
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		numbers.parallelStream().forEach(System.out::println);
	}

	@Test
	public void testPeek(){

		Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());
	}

	@Test
	public void testConcat(){
		Stream<Integer> stream2 = Stream.of(2, 4, 6, 8, 9, 1);
		Stream<Integer> stream1 = Stream.of(1, 3, 5, 7, 9);

		Stream.concat(stream2,stream1).distinct().forEach(System.out::print);
	}

	@Test
	public void testReduce() {
		Optional<Integer> optional = Stream.of(1,2,3).filter(x->x>1).reduce((x, y)->x+y);
		System.out.println(optional.get());
	}


	@Test
	public void testSummaryStatistics(){
		IntSummaryStatistics statistics = Stream.of(2, 4, 6, 8, 9, 1).collect(Collectors.summarizingInt(value -> value));

		System.out.println("Average:"+statistics.getAverage());
		System.out.println("Max:"+statistics.getMax());
		System.out.println("Min:"+statistics.getMin());
		System.out.println("Count:"+statistics.getCount());
		System.out.println("Sum:"+statistics.getSum());


	}
}
