学习笔记

## GCLogAnalysis  案例演练

### 串行化gc

串行gc 单线程执行 

使用命令-XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:SerialGC.long -XX:+UseSerialGC

![image-20201025161946950](.\img\image-20201025161946950.png)

从日志可以看出,串行化GC经过多次YGC后,只执行FGC了

原因很明显,最后一次YGC后,年轻代无法清除,全部满了

因为使用单线程,FGC只有老年代在进行处理,但是每次只释放了很少的堆内存,相当于无效的GC,但是因为单线程,所以整个程序都在执行gc,相当于程序以及挂掉了,所以建议串行化GC最好不要使用

## 并行GC

-XX:+UseParallelGC

java8默认GC,多线程处理

![image-20201025164224927](.\img\image-20201025164224927.png)

并行GC 先是YGC和FGC交替执行,

当执行FGC时,前几次直接把Y区清零,old区被清除的越来越少,直到old区放不下后,Y区才不会清零

### CMSGC

对年轻代采用并行 STW 方式的 mark-copy (标记-复制)算法，对老年代主要使用并发 marksweep (标记-清除)算法  

避免在老年代垃圾收集时出现长时间的卡顿，  默认情况下，CMS 使用的并发线程数等于 CPU 核心数的 1/4。  

-XX:+UseConcMarkSweepGC

![image-20201025165516964](.\img\image-20201025165516964.png)

![image-20201025165533968](.\img\image-20201025165533968.png)

从日志可以看出,先是执行YGC,当YGC无法清理过多的内存是转为CMS gc

CMSgc的步骤:

阶段 1: Initial Mark（初始标记）
阶段 2: Concurrent Mark（并发标记）
阶段 3: Concurrent Preclean（并发预清理）
阶段 4: Final Remark（最终标记）
阶段 5: Concurrent Sweep（并发清除）
阶段 6: Concurrent Reset（并发重置）  

到最后YGC无法有效清除后,频繁发生CMSGC

### G1GC

g1gc堆不再分成年轻代和老年代  

g1gc内存溢出了,在日志结尾发现fgc连续两次,而且没有清除掉,推测可能之后的gc一直清除不了,导致内存溢出

![image-20201025171352626](.\img\image-20201025171352626.png)

Evacuation Pause: young（纯年轻代模式转移暂停）
Concurrent Marking（并发标记）
阶段 1: Initial Mark（初始标记）
阶段 2: Root Region Scan（Root区扫描）
阶段 3: Concurrent Mark（并发标记）
阶段 4: Remark（再次标记）
阶段 5: Cleanup（清理）
Evacuation Pause (mixed)（转移暂停: 混合模式）
Full GC (Allocation Failure)  

# 模拟压测

使用 sb -u http://localhost:8088/api/hello -c 20 -N 60 命令

串行化:

![image-20201027224400510](.\img\image-20201027224400510.png)

并行gc:

![image-20201027224729619](.\img\image-20201027224729619.png)

cmsGC:

​	

![image-20201027225012043](.\img\image-20201027225012043.png)

G1GC:

![image-20201027225318605](.\img\image-20201027225318605.png)

从4种情况来看,并行gc的数据比串行化gc更差,可能是电脑原因,cmsgc,和g1gc明显比另外两种西能好,g1gc的最大处理时间要比smcgc小,可能是cmsgc到后面存有空间碎片导致速度降低

