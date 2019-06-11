# 代码片段

  记录一些平时收集或者自己写的代码片段、Spring配置、项目架构、算法、文章等

## 列表
  - [一致性Hash](https://github.com/liaojiacan/code-snippets/tree/master/consistent-hash) ,收集自[《对一致性Hash算法，Java代码实现的深入研究》](http://www.cnblogs.com/xrq730/p/5186728.html)
  - [HTML生成PDF , flying-saucer-pdf](https://github.com/liaojiacan/code-snippets/tree/master/flying-saucer-pdf)
  - [foundation-emails-template,生成兼容各种邮件客户端的邮件模版](https://github.com/zurb/foundation-emails-template)
  - [memcached 的一些使用](https://github.com/liaojiacan/code-snippets/tree/master/memcache)
  - [redis 的一些使用](https://github.com/liaojiacan/code-snippets/tree/master/redis)
  - [payload ,记录java相关的一些0day](https://github.com/liaojiacan/code-snippets/tree/master/payload)
  - [Spring项目定时任务-quartz](https://github.com/liaojiacan/code-snippets/tree/master/spring-quartz-demo)
  - [Spring项目将*.properties存储到zookeeper,适合分布式项目](https://github.com/liaojiacan/code-snippets/tree/master/spring-zookeeper-property-placeholder)
  - [javaagent对java程序的一些Hack](https://github.com/liaojiacan/code-snippets/tree/master/javaagent)
  - [javassist破解收费IDEA插件iedis](https://github.com/liaojiacan/code-snippets/blob/master/javaagent/src/main/java/com/github/liaojiacan/demo/IedisCracker.java)
  - [redis实现附近的人](https://github.com/liaojiacan/code-snippets/blob/master/redis/src/main/java/com/github/liaojiacan/geo/RedisPeopleNearByServiceImpl.java)
  - [java 8](https://github.com/liaojiacan/code-snippets/tree/master/java-language/src/main/java/com/github/liaojiacan/java8)

### Java 语言特性相关

- [类初始化先后顺序](https://github.com/liaojiacan/code-snippets/tree/master/java-language/src/main/java/com/github/liaojiacan/classinit)
- [对存在空值的集合进行排序](https://github.com/liaojiacan/code-snippets/blob/master/java-language/src/main/java/com/github/liaojiacan/comparator/Example.java)
- [并发包中的一些使用用例/ExecutorService/Future/CountDownLatch/CyclicBarrier](https://github.com/liaojiacan/code-snippets/tree/master/java-language/src/main/java/com/github/liaojiacan/concurrent)
- [Java8 lambda的使用用例](https://github.com/liaojiacan/code-snippets/tree/master/java-language/src/main/java/com/github/liaojiacan/java8/lambda)
- [Java8 Stream的使用用例](https://github.com/liaojiacan/code-snippets/blob/master/java-language/src/main/java/com/github/liaojiacan/java8/stream/Example.java)
- [利用反射获取包级别限制的类的静态成员](https://github.com/liaojiacan/code-snippets/blob/master/java-language/src/main/java/com/github/liaojiacan/reflection/ReflectAClassWithPackageLevelAccess.java)
- [Unsafe操作数组的使用测试用例](https://github.com/liaojiacan/code-snippets/blob/master/java-language/src/main/java/com/github/liaojiacan/unsafe/UnsafeArrayOperationTests.java)
- [自定义ClassLoader实现一个支持热加载的应用启动器](https://github.com/liaojiacan/code-snippets/issues/8)  2019-03-17

### 并发编程

  - [JDK1.7中ConcurrentHashMap的源码解读](https://github.com/liaojiacan/code-snippets/issues/6) 2019-02-25
  - [Future业务场景---分解任务，主线程汇总最终的结果](https://github.com/liaojiacan/code-snippets/blob/master/java-language/src/main/java/com/github/liaojiacan/concurrent/executor/AsynchronousTaskResult.java)
  - [使用wait和notify实现多线程交替打印](https://github.com/liaojiacan/code-snippets/blob/master/java-language/src/main/java/com/github/liaojiacan/thread/MultiThreadPrint.java)

### 数据结构和算法
- 排序算法
    - [冒泡排序](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/sort/BubbleSort.java)
    - [插入排序](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/sort/InsertionSort.java)
    - [选择排序](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/sort/SelectionSort.java)
    - [快速排序](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/sort/QuickSort.java)
    - [归并排序](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/sort/MergeSort.java)
    - [堆排序](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/sort/HeapSort.java)
- 小算法
    - [检查一个数是否是2的N次方](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/LittleAlgorithms/CheckIsNthPowerOf2.java)
    - [一个100大小的数组 ，1~99 的数都存在，求哪个数是重复的。](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/search/FindDuplicateNum.java)
    - [获取originList的 大小为combinationSize的T元素的所有组合](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/combine/Combiner.java)
- 练习
    - [写一段死锁的代码](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/coding/%E5%86%99%E4%B8%80%E6%AE%B5%E6%AD%BB%E9%94%81%E7%9A%84%E4%BB%A3%E7%A0%81/DeadLockTest.java)
    - [有三个线程ID分别是A、B、C,请用多线编程实现，在屏幕上循环打印10次ABCABC](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/coding/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%BE%AA%E7%8E%AF%E6%89%93%E5%8D%B0/Solution.java)
    - [实现LinkedList的add和remove方法](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/coding/%E5%AE%9E%E7%8E%B0LinkedList%E7%9A%84add%E5%92%8Cremove%E6%96%B9%E6%B3%95/LinkedList.java)
    - [收益分摊算法（这个题不是很理解）](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/coding/%E6%94%B6%E7%9B%8A%E5%88%86%E6%91%8A%E7%AE%97%E6%B3%95/Solution.java)
    - [红包算法：输入红包金额和领取人数，返回每个人领取的红包金额,金额单位为分](https://github.com/liaojiacan/code-snippets/blob/master/data-structure-and-algorithms/src/main/java/com/github/liaojiacan/coding/%E7%BA%A2%E5%8C%85%E7%AE%97%E6%B3%95/RedPacketTest.java)

## 业务开发

  - [抽奖业务设计](https://github.com/liaojiacan/code-snippets/tree/master/function-design/src/main/java/com/github/liaojiacan/lottery)
