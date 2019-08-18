package com.github.liaojiacan.disruptor;


import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @auth liaojiacan
 * @date 2019/8/18
 * @desc 测试10000个任务在使用Disruptor来执行有多少延时的情况。
 */
public class ExecutorByDisruptorTest {

    public static class GameOverEvent {
        private String roomId;
        private long gameStart;

        public GameOverEvent() {
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public long getGameStart() {
            return gameStart;
        }

        public void setGameStart(long gameStart) {
            this.gameStart = gameStart;
        }
    }

    public static class GameOverEventFactory implements EventFactory<GameOverEvent> {
        @Override
        public GameOverEvent newInstance() {
            return new GameOverEvent();
        }
    }

    public static class GameOverEventHandler implements EventHandler<GameOverEvent> {

        @Override
        public void onEvent(GameOverEvent gameOverEvent, long l, boolean b) throws Exception {
            System.out.println(Thread.currentThread().getName() + " task " + gameOverEvent.getRoomId() + " Delay:" + (System.currentTimeMillis() - gameOverEvent.getGameStart()) + "ms");
        }
    }


    public static void main(String[] args) {

        EventFactory eventFactory = new GameOverEventFactory();

        int ringBufferSize = 1024 * 1024;

        Disruptor<GameOverEvent> disruptor = new Disruptor<GameOverEvent>(eventFactory, ringBufferSize, new ThreadFactory() {

            private AtomicInteger count = new AtomicInteger();

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "Thread-" + count.incrementAndGet());
            }
        }, ProducerType.SINGLE, new BusySpinWaitStrategy());
        EventHandler<GameOverEvent> eventHandler = new GameOverEventHandler();
        disruptor.handleEventsWith(eventHandler);//连接handler
        disruptor.start();//启动disruptor，启动所有线程
        //从disruptor中获得ringBuffer用于发布
        RingBuffer<GameOverEvent> ringBuffer = disruptor.getRingBuffer();
        long startTime = System.currentTimeMillis();
        IntStream.range(0, 10000).forEach(id -> {
            ringBuffer.publishEvent((gameOverEvent, l) -> {
                gameOverEvent.setRoomId(id + "");
                gameOverEvent.setGameStart(startTime);
            });
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Exit");
        }));

    }

}


