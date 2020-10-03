package com.github.liaojiacan.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @auth liaojiacan
 * @date 2019/8/18
 * @desc 测试10000个任务使用Jdk的线程池来运行会导致的延时是多久。
 */
public class ExecutorByJdkThreadPoolTest {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2, new ThreadFactory() {

            private AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "Thread-" + count.incrementAndGet());
            }
        });

        // 8 core 的情况，最大的任务延时有270ms
        IntStream.range(0, 10000).forEach(id -> {
            long startTime = System.currentTimeMillis();
            executorService.execute(() -> {
                byte[] data = new byte[1024];
                System.out.println(Thread.currentThread().getName() + " task " + id + " Delay:" + (System.currentTimeMillis() - startTime) + "ms");
            });
        });

    }
}
