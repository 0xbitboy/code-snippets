package com.github.liaojiacan.unsafe;

import org.junit.Test;
import sun.misc.Unsafe;

/**
 * @author liaojiacan
 * @date 2019/2/25
 */
public class UnsafeArrayOperationTests {


	static final class Segment<K,V> {
		K key;
		V value;

		public Segment(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	// Unsafe
	private static final Unsafe UNSAFE;
	// Segment[0] 的起始偏移量
	private static final long SBASE;
	// 用于计算 i*len(Segment[0]) 的位运算位移数
	private static final long SSHIFT;

	static {
		UNSAFE = UnsafeUtil.getUnsafe();
		Class sc = Segment[].class;
		SBASE = UNSAFE.arrayBaseOffset(sc);
		int ssize = UNSAFE.arrayIndexScale(sc);
		// 这个公式是计算一个32位整型的对数 floor(log2(x)) == 31 - Integer.numberOfLeadingZeros(x);
		SSHIFT = 31 - Integer.numberOfLeadingZeros(ssize);
	}

	@Test
	public void testGetObject(){
		Segment<String,Integer>[] segments = new Segment[10];
		for(int i=0;i<segments.length;++i){
			putSegment(segments,i,new Segment<>(i+"",i));
		}
		for(int i=0;i<segments.length;++i){
			System.out.println(getSegmentWithoutMultiply(segments,i).value);
		}

	}

	/**
	 * 获取 数组中 i元素的内存偏移
	 * @param i
	 * @return
	 */
	private long getArrayObjectOffset(int i){
		//等价与 SBASE+i*len(segment[0])
		return SBASE +(i<<SSHIFT);
	}

	/**
	 * 往数组中设置元素
	 * @param segments
	 * @param index
	 * @param segment
	 */
	private void putSegment(Segment<String, Integer>[] segments,int index,Segment segment){
		UNSAFE.putOrderedObject(segments,getArrayObjectOffset(index),segment);
	}

	/**
	 * 通过Unsafe 和内存偏移直接获取数组中的元素。
	 * @param segments
	 * @param i
	 * @return
	 */
	private Segment<String, Integer> getSegment(Segment<String, Integer>[] segments, int i) {
		// segment[0] 的偏移地址
		int baseOffset = UNSAFE.arrayBaseOffset(Segment[].class);
		// 每个位置的大小
		int indexScale = UNSAFE.arrayIndexScale(Segment[].class);
		long offset = baseOffset+i*indexScale ;
		return (Segment<String,Integer>) (Segment) UNSAFE.getObject(segments, offset);
	}

	/**
	 * 通过Unsafe 和内存偏移直接获取数组中的元素。用位运算代替乘法运算计算偏移
	 * @param segments
	 * @param i
	 * @return
	 */
	private Segment<String, Integer> getSegmentWithoutMultiply(Segment<String, Integer>[] segments, int i) {
		long offset = getArrayObjectOffset(i);
		return (Segment<String,Integer>) (Segment) UNSAFE.getObject(segments, offset);
	}
}
