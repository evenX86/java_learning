>
许多情况下，在一个程序中使用多线程是有益处的，可以大大提高程序的效率，多线程主要有以下3个优点1,资源利用率更好2，程序设计在某些情况下更简单3，程序响应更快。当然凡事有利就有弊，多线程也会使程序的开发，维护及调试更加复杂，当然如果我们能够扬长避短，在正确的场合下使用多线程，那么它将成为我们程序中开发的利器。


Java一直以来，对多线程的开发支持比较良好，特别在JDK5,6后引入的java.util.concurrent包，使用多线程的开发变的更加容易，这个包里面大部分的API都是更进一步的封装，作为开发者，我们只需熟悉它怎么使用，就能够很轻松的上手，当然如果你想完全搞懂它，那么就需要读读那些优秀的源码了。

ForkJoinPool这个类是JDK7后新增的线程池，很适合在单机多核的PC上部署多线程程序，ForkJoinPool使用的分而治之的思想，这一点与当前很火的大数据处理框架Hadoop的map/reduce思想非常类似，但是他们的使用场合却不一样，ForkJoinPool适合在一台PC多核CPU上运行，而hadoop则适合在分布式环境中进行大规模集群部署。

Fork/Join 模式有自己的适用范围。如果一个应用能被分解成多个子任务，并且组合多个子任务的结果就能够获得最终的答案，那么这个应用就适合用 Fork/Join 模式来解决.

ForkJoinPool使用的工作窃取的方式能够在最大方式上充分利用CPU的资源，一般流程是fork分解，join结合。本质是将一个任务分解成多个子任务，每个子任务用单独的线程去处理，主要几个常用方法有
fork( ForkJoinTask)	异步执行一个线程
join( ForkJoinTask)	等待任务完成并返回执行结果
execute( ForkJoinTask)	执行不带返回值的任务
submit( ForkJoinTask)	执行带返回值的任务
invoke( ForkJoinTask)	执行指定的任务，等待完成，返回结果。
invokeAll(ForkJoinTask)	执行指定的任务，等待完成，返回结果。
shutdown()	执行此方法之后，ForkJoinPool 不再接受新的任务，但是已经提交的任务可以继续执行。如果希望立刻停止所有的任务，可以尝试 shutdownNow() 方法。
awaitTermination(int, TimeUnit.SECONDS)	阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束。
compute（）	执行任务的具体方法
Runtime.getRuntime().availableProcessors()	获取CPU个数的方法

关于上表中的ForkJoinTask是一个抽象类，代表一个可以fork与join的任务. ,它还有2个抽象子类:RecursiveAcion和RecursiveTask.其中RecursiveTask代表有泛型返回值的任务.而RecursiveAction代表没有返回值.