package com.github.liaojiacan.jvm;

/**
 * @auth liaojiacan
 * @date 2019/8/17
 * @desc CPU Cache 缓存的影响测试
 * @see <a href="https://tech.meituan.com/2016/11/18/disruptor.html">缓存的影响测试</a>
 */
public class CacheLineEffectTest {
    //考虑一般缓存行大小是64字节，一个 long 类型占8字节
    static long[][] arr;

    public static void main(String[] args) {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            // 一个long 8个字节，8个连续的long一共64字节，刚好一个缓存行
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0L;
        long marked = System.currentTimeMillis();
        // 每次读取刚好一个缓存行，CPU读取第一个值的时候，会将后续的7个值一起读取到Cache Line中。
        // 这里的2个循环只会从主内存加载1024*1024次
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");

        marked = System.currentTimeMillis();
        // 这里每次读取的是每64字节中的的一个8字节，也就是循环会从主内存中加载 1024*1024*8次
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum = arr[j][i];
            }
        }
        System.out.println("Loop times:" + (System.currentTimeMillis() - marked) + "ms");
    }
}
