package com.github.liaojiacan.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liaojiacan
 * @date 2018/11/29
 */
public class Example {


	@Test
	public void testList2Map(){
		List<PrizePO> prizePOList = Arrays.asList(new PrizePO(1L,"iPhone XR Max", 1L),
				new PrizePO(2L,"iPhone X ", 1L),
		new PrizePO(3L,"iPhone SR", 2L));
		Map<Long, PrizePO> cache = prizePOList.stream().collect(Collectors.toMap(PrizePO::getPrizeId, Function.identity(), (pre, cur) -> pre));
	}

	@Test
	public void testListGroupBy(){
		List<PrizePO> prizePOList = Arrays.asList(new PrizePO(1L,"iPhone XR Max", 1L),
				new PrizePO(2L,"iPhone X ", 1L),
				new PrizePO(3L,"iPhone SR", 2L));
		Map<Long, List<PrizePO>> activityPrizeCache = prizePOList.stream().collect(Collectors.groupingBy(PrizePO::getActivityId));
	}

	@Test
	public void testListSort(){
		List<PrizePO> prizePOList = Arrays.asList(new PrizePO(1L,"iPhone XR Max", 1L),
				new PrizePO(2L,"iPhone X ", 1L),
				new PrizePO(3L,"iPhone SR", 2L));
		List<PrizePO> sortBySeq = prizePOList.stream().sorted(Comparator.comparing(PrizePO::getSequence)).collect(Collectors.toList());
		List<PrizePO> sortBySeqAndPrice  = prizePOList.stream().sorted(Comparator.comparing(PrizePO::getSequence).thenComparing(PrizePO::getPrice)).collect(Collectors.toList());
	}

	@Test
	public void testListSum(){
		List<PrizePO> prizePOList = Arrays.asList(new PrizePO(1L,"iPhone XR Max", 1L),
				new PrizePO(2L,"iPhone X ", 1L),
				new PrizePO(3L,"iPhone SR", 2L));

		int sum = prizePOList.stream().mapToInt(PrizePO::getPrice).sum();
	}

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


	public static class PrizePO{
		private Long prizeId;
		private String prizeName;
		private Long activityId;
		private Integer sequence;
		private Integer price;

		public PrizePO(Long prizeId, String prizeName, Long activityId) {
			this.prizeId = prizeId;
			this.prizeName = prizeName;
			this.activityId = activityId;
		}

		public Long getPrizeId() {
			return prizeId;
		}

		public void setPrizeId(Long prizeId) {
			this.prizeId = prizeId;
		}

		public String getPrizeName() {
			return prizeName;
		}

		public void setPrizeName(String prizeName) {
			this.prizeName = prizeName;
		}

		public Long getActivityId() {
			return activityId;
		}

		public void setActivityId(Long activityId) {
			this.activityId = activityId;
		}

		public Integer getSequence() {
			return sequence;
		}

		public void setSequence(Integer sequence) {
			this.sequence = sequence;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}
	}
}
