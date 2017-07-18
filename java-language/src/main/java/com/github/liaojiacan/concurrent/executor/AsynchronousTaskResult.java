package com.github.liaojiacan.concurrent.executor;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 等待异步操作结果
 * 业务场景：1.一个业务由多个子任务组成，那么把任务分解成多个子任务去异步执行。eg 根据n个不同参数去执行某个函数，然后获取每次的结果的集合。
 * Created by liaojiacan on 2017/7/18.
 */
public class AsynchronousTaskResult {



    public static String execute(Integer i){
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i.toString();
    }

    public static void main(String[] args) {

        ExecutorService ex = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();
        for(int i =0;i<100;i++){
            int finalI = i;
            //submit之后任务就会被加入到队列里面执行
            futures.add(ex.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println(Thread.currentThread().getName()+"-worker-working for "+finalI);
                    return execute(finalI);
                }
            }));
        }

        List<String> rs = new ArrayList<>();
        //获取每个任务返回的结果
        for(Future<String> future:futures){
            try {
                rs.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(StringUtils.join(rs,","));


    }
}
