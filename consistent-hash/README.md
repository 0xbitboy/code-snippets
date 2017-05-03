# 一致性HASH算法

  解决分布式服务节点均衡性的算法。
## 概述

1. 将server 组成环，（0～2^ 32 个点，顺时针找到第一个节点）。
2. 增加server的虚拟节点，再利用hash算法将所有的虚拟节点均衡散落在环上面。
3. 数据结构采用sortMap，tailMap(key) 的第一个节点就是顺时针的第一个点，找不到就采用环的第一个节点。

## 文章
- [《对一致性Hash算法，Java代码实现的深入研究》](http://www.cnblogs.com/xrq730/p/5186728.html)
- [《MemCache详细解读》](http://www.cnblogs.com/xrq730/p/4948707.html)
- [《memcache的一致性hash算法使用》](http://get.ftqq.com/7057.get)
