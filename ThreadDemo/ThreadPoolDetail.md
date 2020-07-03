##### 使用线程池的优点？

a.第一点，线程池可以解决线程生命周期的系统开销问题，同事还可以加快响应速度。因为线程池中的线程是可以复用，我们只需要少量的线程去执行大量的任务，这就大大减少了线程生命周期的开销。而且线程通常不是等接到任务才临时创建，而是已经创建好等待去执行任务，这样也就消除了创建所带来的延迟，提升了响应速度，增强用户体验。

b.第二点，线程池可以统筹内存和CPU的使用，避免了资源使用不当。线程池会根据配置和任务数量灵活地控制线程数量，不够的时候就创建，太多的时候就回收，避免线程过多引起内存溢出，或线程过少导致CPU资源浪费，达到一个完美的平衡。

c.第三点，线程可以统一管理资源。比如线程池可以统一管理任务队列和线程，可以统一开始或结束任务，比单个线程逐一处理任务更方便，更易于管理，同时也有利于数据统计。

##### 线程池各个参数的含义及线程池的大小

```java
 public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
       
    }
```

线程池的参数：

corePoolSize：核心线程数

maximumPoolSize：最大线程数

keepAliveTime+unit:空闲线程的存活时间，可以用 setKeepAliveTime 方法动态改变 keepAliveTime 的参数值。

workQueue：存放任务的队列

threadFactory：线程工厂，用于创建新线程

handler：处理被拒绝的任务（4种任务拒绝策略）

线程池的几个特点

线程池希望保持较少的线程数，并且只有在负载变得很大时才增加线程。

线程池只有在任务队列填满时才创建多于 corePoolSize 的线程，如果使用的是无界队列（例如 LinkedBlockingQueue），那么由于队列不会满，所以线程数不会超过 corePoolSize。

通过设置 corePoolSize 和 maxPoolSize 为相同的值，就可以创建固定大小的线程池。

通过设置 maxPoolSize 为很高的值，例如 Integer.MAX_VALUE，就可以允许线程池创建任意多的线程。

**<u>实际使用中并发线程数如何设置：</u>**
		**<u>*IO密集型 =2Ncpu*</u>**

**<u>*计算密集型=Ncpu（常出现于线程中：复杂算法）*</u>**

**<u>*java中：Ncpu=Runtime.getRuntime().availableProcessors();*</u>**

**<u>*还有一种说法：计算密集型=Ncpu+1（即对于计算密集型的任务，在拥有N个处理器的系统上，当线程池的大小为N+1时，通常能实现最优的效率。(即使当计算密集型的线程偶尔由于缺失故障或者其他原因而暂停时，这个额外的线程也能确保CPU的时钟周期不会被浪费。但是这种做法导致的多一个cpu上下文切换是否值得，这里不考虑。）*</u>**

注：Nthreads=Ncpu*Ucpu*(1+w/c)，其中

​		Ncpu=CPU核心数

​		Ucpu=cpu使用率，0~1

​		W/C=等待时间与计算时间的比率

##### 线程池的四种拒绝策略

拒绝时机

首先，新建线程池时可以指定它的任务拒绝策略，例如：

```java
newThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
   new ThreadPoolExecutor.DiscardOldestPolicy());
```

以便在必要的时候按照我们的策略来拒绝任务，那么拒绝任务的时机是什么呢？线程池会在以下两种情况下会拒绝新提交的任务。

a.第一种是我们调用shutdown()等方法关机线程池后，几遍此时线程池中内部可能依然由没执行完的任务，但是由于线程池关闭，此时再向线程池中提交任务，就会遭到拒绝。

b.第二种就是线程池没有能力处理新提交的任务，也就是工作非常饱和的时候。

分析一下第二种情况，比如新建一个线程池，使用上线为10的ArrayBlockingQueue为任务队列，并且指定核心线程数为5，最大线程数为10，假设此时有20条耗时任务被提交，在这种情况下，线程池首先创建核心线程数量的线程，也就是5个线程来执行任务，然后往任务队列中去放任务，队列里面10个容量被放满之后，会继续创建新线程，直到到达最大线程数10。此时线程池一共20个任务，其中10个任务正在被10个线程执行，还有10个任务在任务队列中等待，由于线程池的最大线程数量就是10，所以已经不能再增加更多的线程来帮忙处理任务了，这就意味着此时线程池饱和，这个时候再提交新任务就会被拒绝。

![](/Users/guanghui/Downloads/CgoB5l3g0XCAWJKOAABzAQJB4SM657.png)

我们了解了线程池拒绝任务的时机，那么我们如何正确地选择拒绝策略呢？Java 在 ThreadPoolExecutor 类中为我们提供了 4 种默认的拒绝策略来应对不同的场景，都实现了 RejectedExecutionHandler 接口，如图所示：

![img](https://s0.lgstatic.com/i/image2/M01/AE/B0/CgotOV3g0WWAVWVlAAEsBI6lEEA162.png)

a.第一种拒绝策略时AbortPolicy，这种拒绝策略在拒绝任务时会直接抛出一个类型为RejectedExecutionException的RuntimeException，让你感知到任务被拒绝了，于是你可以根据业务逻辑选择重试或者放弃提交策略。

b.第二章拒绝策略时DiscardPolicy，这种拒绝策略正如它的名字描述的一样，当新任务被提交之后直接丢弃，也不会给你任何通知，相对而言存在一定的风险，因为我们在提交任务的时候根本不知道此任务会被丢弃，可能会造成数据丢失。

c.第三种拒绝策略是DiscareOldestPolicy，如果线程池没能力执行，则会丢弃任务队列里面的头节点，通常是存活时间最长的任务与第二种不同之处就是它丢弃的不是最新提交的，而是队列中存活时间最长的，这样就会腾出空间给最新提交的任务，也存在一定的数据丢失风险。

d.第四种拒绝策略时CallerRunsPolicy，相对而言它就比较完善了，当有新任务提交后，如果线程没被关闭且没由能力执行，则把这个任务交于提交任务的线程去执行，也就是谁提交任务谁负责执行任务。

​		这样做有两个好处：

​				新提交的任务不会被丢弃，也就不会造成业务损失。

​				由于谁提交任务谁就要负责执行任务，这样提交任务的线程就得负责执行任务，而执行任务又是比				较费时的，在这段时间，提交任务的线程被占用，也就不会再提交新的任务任务，减缓了任务提交的速				度，相当于是一个负反馈。在此期间，线程池中的线程可以充分利用这段时间去执行一部分任务，相当				是给线程池一定的缓冲期。

##### 有哪 6 种常见的线程池？什么是 Java8 的 ForkJoinPool？

6 种常见的线程池如下。

FixedThreadPool

CachedThreadPool

ScheduledThreadPool

SingleThreadExecutor

SingleThreadScheduledExecutor

ForkJoinPool

###### FixedThreadPool

第一种线程池叫作 FixedThreadPool，它的核心线程数和最大线程数是一样的，所以可以把它看作是固定线程数的线程池，它的特点是线程池中的线程数除了初始阶段需要从 0 开始增加外，之后的线程数量就是固定的，就算任务数超过线程数，线程池也不会再创建更多的线程来处理任务，而是会把超出线程处理能力的任务放到任务队列中进行等待。而且就算任务队列满了，到了本该继续增加线程数的时候，由于它的最大线程数和核心线程数是一样的，所以也无法再增加新的线程了。

![img](https://s0.lgstatic.com/i/image2/M01/AF/A0/CgotOV3kzoeARRniAAAwS8Pup4A734.png)

如图所示，线程池有 t0~t9，10 个线程，它们会不停地执行任务，如果某个线程任务执行完了，就会从任务队列中获取新的任务继续执行，期间线程数量不会增加也不会减少，始终保持在 10 个。

###### CachedThreadPool

第二种线程池是 CachedThreadPool，可以称作可缓存线程池，它的特点在于线程数是几乎可以无限增加的（实际最大可以达到 Integer.MAX_VALUE，为 2^31-1，这个数非常大，所以基本不可能达到），而当线程闲置时还可以对线程进行回收。也就是说该线程池的线程数量不是固定不变的，当然它也有一个用于存储提交任务的队列，但这个队列是 SynchronousQueue，队列的容量为0，实际不存储任何任务，它只负责对任务进行中转和传递，所以效率比较高。

当我们提交一个任务后，线程池会判断已创建的线程中是否有空闲线程，如果有空闲线程则将任务直接指派给空闲线程，如果没有空闲线程，则新建线程去执行任务，这样就做到了动态地新增线程。让我们举个例子，如下方代码所示。

```java
ExecutorService service = Executors.newCachedThreadPool();
    for (int i = 0; i < 1000; i++) { 
        service.execute(new Task() { 
    });
 }
```

使用 for 循环提交 1000 个任务给 CachedThreadPool，假设这些任务处理的时间非常长，会发生什么情况呢？因为 for 循环提交任务的操作是非常快的，但执行任务却比较耗时，就可能导致 1000 个任务都提交完了但第一个任务还没有被执行完，所以此时 CachedThreadPool 就可以动态的伸缩线程数量，随着任务的提交，不停地创建 1000 个线程来执行任务，而当任务执行完之后，假设没有新的任务了，那么大量的闲置线程又会造成内存资源的浪费，这时线程池就会检测线程在 60 秒内有没有可执行任务，如果没有就会被销毁，最终线程数量会减为 0。

###### ScheduledThreadPool

第三个线程池是 ScheduledThreadPool，它支持定时或周期性执行任务。比如每隔 10 秒钟执行一次任务，而实现这种功能的方法主要有 3 种，如代码所示：



```java
ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
 
service.schedule(new Task(), 10, TimeUnit.SECONDS);
 
service.scheduleAtFixedRate(new Task(), 10, 10, TimeUnit.SECONDS);
 
service.scheduleWithFixedDelay(new Task(), 10, 10, TimeUnit.SECONDS);
```

那么这 3 种方法有什么区别呢？

第一种方法 schedule 比较简单，表示延迟指定时间后执行一次任务，如果代码中设置参数为 10 秒，也就是 10 秒后执行一次任务后就结束。

第二种方法scheduleAtFixedRate表示以固定的频率执行任务，第二个参数initialDelay表示第一次延时时间，第三个参数period表示周期，也就是第一次延时之后每次延长多长时间执行一次任务。

第三种方法scheduleWithFixedDelay与第二种类似，也是周期执行任务，区别在于对周期的定义，之前的scheduleAtFixedRate是以任务开始的时间为时间的起点开始计时，时间到就执行第二次任务，二不管任务需要花多久执行；而scheduleWithFixedDelay方法以任务结束的时间为下一次循环时间起点开始计时。

举个例子，假设某个同学正在熬夜写代码，需要喝咖啡来提神，假设每次喝咖啡都需要花10分钟的时间，如果此时采用第2种方法 scheduleAtFixedRate，时间间隔设置为 1 小时，那么他将会在每个整点喝一杯咖啡，以下是时间表：

00:00: 开始喝咖啡

00:10: 喝完了

01:00: 开始喝咖啡

01:10: 喝完了

02:00: 开始喝咖啡

02:10: 喝完了

但是假设他采用第3种方法 scheduleWithFixedDelay，时间间隔同样设置为 1 小时，那么由于每次喝咖啡需要10分钟，而 scheduleWithFixedDelay 是以任务完成的时间为时间起点开始计时的，所以第2次喝咖啡的时间将会在1:10，而不是1:00整，以下是时间表：

00:00: 开始喝咖啡

00:10: 喝完了

01:10: 开始喝咖啡

01:20: 喝完了

02:20: 开始喝咖啡

02:30: 喝完了

###### SingleThreadExecutor

第四种线程池是 SingleThreadExecutor，它会使用唯一的线程去执行任务，原理和 FixedThreadPool 是一样的，只不过这里线程只有一个，如果线程在执行任务的过程中发生异常，线程池也会重新创建一个线程来执行后续的任务。这种线程池由于只有一个线程，所以非常适合用于所有任务都需要按被提交的顺序依次执行的场景，而前几种线程池不一定能够保障任务的执行顺序等于被提交的顺序，因为它们是多线程并行执行的。

###### SingleThreadScheduledExecutor

第五个线程池是 SingleThreadScheduledExecutor，它实际和第三种 ScheduledThreadPool 线程池非常相似，它只是 ScheduledThreadPool 的一个特例，内部只有一个线程，如源码所示：

```java
new ScheduledThreadPoolExecutor(1)
```

它只是将SingleThreadExecutor的核心线程数设置为1了。

![img](https://s0.lgstatic.com/i/image2/M01/AF/80/CgoB5l3kzomAckv5AAAxf6FCPco696.png)

总结上述的五种线程池，我们以核心线程数、最大线程数，以及线程存活时间三个维度进行对比，如表格所示。

###### ForkJoinPool

 ![img](https://s0.lgstatic.com/i/image2/M01/AF/A0/CgotOV3kzomAflZxAAB99x9-MzI241.png)

最后，我们来看下第六种线程池 ForkJoinPool，这个线程池是在 JDK 7 加入的，它的名字 ForkJoin 也描述了它的执行机制，主要用法和之前的线程池是相同的，也是把任务交给线程池去执行，线程池中也有任务队列来存放任务。但是 ForkJoinPool 线程池和之前的线程池有两点非常大的不同之处。第一点它非常适合执行可以产生子任务的任务。

如图所示，我们有一个 Task，这个 Task 可以产生三个子任务，三个子任务并行执行完毕后将结果汇总给 Result，比如说主任务需要执行非常繁重的计算任务，我们就可以把计算拆分成三个部分，这三个部分是互不影响相互独立的，这样就可以利用 CPU 的多核优势，并行计算，然后将结果进行汇总。这里面主要涉及两个步骤，第一步是拆分也就是 Fork，第二步是汇总也就是 Join，到这里你应该已经了解到 ForkJoinPool 线程池名字的由来了。

如图所示，我们有一个 Task，这个 Task 可以产生三个子任务，三个子任务并行执行完毕后将结果汇总给 Result，比如说主任务需要执行非常繁重的计算任务，我们就可以把计算拆分成三个部分，这三个部分是互不影响相互独立的，这样就可以利用 CPU 的多核优势，并行计算，然后将结果进行汇总。这里面主要涉及两个步骤，第一步是拆分也就是 Fork，第二步是汇总也就是 Join，到这里你应该已经了解到 ForkJoinPool 线程池名字的由来了。

举个例子，比如面试中经常考到的菲波那切数列，你一定非常熟悉，这个数列的特点就是后一项的结果等于前两项的和，第 0 项是 0，第 1 项是 1，那么第 2 项就是 0+1=1，以此类推。我们在写代码时应该首选效率更高的迭代形式或者更高级的乘方或者矩阵公式法等写法，不过假设我们写成了最初版本的递归形式，伪代码如下所示：

```java
if (n <= 1) {
    return n;
 } else {
    Fib f1 = new Fib(n - 1);
    Fib f2 = new Fib(n - 2);
    f1.solve();
    f2.solve();
    number = f1.number + f2.number;
    return number;
 }
```

你可以看到如果 n<=1 则直接返回 n，如果 n>1 ，先将前一项 f1 的值计算出来，然后往前推两项求出 f2 的值，然后将两值相加得到结果，所以我们看到在求和运算中产生了两个子任务。计算 f(4) 的流程如下图所示。

![img](https://s0.lgstatic.com/i/image2/M01/AF/80/CgoB5l3kzoqAZgXiAACbX2rJCR4889.png)

在计算 f(4) 时需要首先计算出 f(2) 和 f(3)，而同理，计算 f(3) 时又需要计算 f(1) 和 f(2)，以此类推。

![img](https://s0.lgstatic.com/i/image2/M01/AF/A0/CgotOV3kzoqAUlPyAADYOKK1PgM516.png)

这是典型的递归问题，对应到我们的 ForkJoin 模式，如图所示，子任务同样会产生子子任务，最后再逐层汇总，得到最终的结果。

ForkJoinPool 线程池有多种方法可以实现任务的分裂和汇总，其中一种用法如下方代码所示。

```java
class Fibonacci extends RecursiveTask<Integer> { 
 
    int n;
 
    public Fibonacci(int n) { 
        this.n = n;
    } 
 
    @Override
    public Integer compute() { 
        if (n <= 1) { 
            return n;
        } 
    Fibonacci f1 = new Fibonacci(n - 1);
    f1.fork();
    Fibonacci f2 = new Fibonacci(n - 2);
    f2.fork();
    return f1.join() + f2.join();
    } 
 }
```

RecursiveTask 类是对ForkJoinTask 的一个简单的包装，这时我们重写 compute() 方法，当 n<=1 时直接返回，当 n>1 就创建递归任务，也就是 f1 和 f2，然后我们用 fork() 方法分裂任务并分别执行，最后在 return 的时候，使用 join() 方法把结果汇总，这样就实现了任务的分裂和汇总。

```java
public static void main(String[] args) throws ExecutionException, InterruptedException { 
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    for (int i = 0; i < 10; i++) { 
        ForkJoinTask task = forkJoinPool.submit(new Fibonacci(i));
        System.out.println(task.get());
    } 
 }
```

结果如下：

```c++
0
1
1
2
3
5
8
13
21
34
```

这就是 ForkJoinPool 线程池和其他线程池的第一点不同。

我们来看第二点不同，第二点不同之处在于内部结构，之前的线程池所有的线程共用一个队列，但 ForkJoinPool 线程池中每个线程都有自己独立的任务队列，如图所示。

![img](https://s0.lgstatic.com/i/image2/M01/AF/80/CgoB5l3kzouAdfLfAAARK97hw4g233.png)

ForkJoinPool 线程池内部除了有一个共用的任务队列之外，每个线程还有一个对应的双端队列 deque，这时一旦线程中的任务被 Fork 分裂了，分裂出来的子任务放入线程自己的 deque 里，而不是放入公共的任务队列中。如果此时有三个子任务放入线程 t1 的 deque 队列中，对于线程 t1 而言获取任务的成本就降低了，可以直接在自己的任务队列中获取而不必去公共队列中争抢也不会发生阻塞（除了后面会讲到的 steal 情况外），减少了线程间的竞争和切换，是非常高效的。

![img](https://s0.lgstatic.com/i/image2/M01/B0/57/CgotOV3nFTCAKmNtAAES7A18i8M873.png)

我们再考虑一种情况，此时线程有多个，而线程 t1 的任务特别繁重，分裂了数十个子任务，但是 t0 此时却无事可做，它自己的 deque 队列为空，这时为了提高效率，t0 就会想办法帮助 t1 执行任务，这就是“work-stealing”的含义。

双端队列 deque 中，线程 t1 获取任务的逻辑是后进先出，也就是LIFO（Last In Frist Out），而线程 t0 在“steal”偷线程 t1 的 deque 中的任务的逻辑是先进先出，也就是FIFO（Fast In Frist Out），如图所示，图中很好的描述了两个线程使用双端队列分别获取任务的情景。你可以看到，使用 “work-stealing” 算法和双端队列很好地平衡了各线程的负载。

![img](https://s0.lgstatic.com/i/image2/M01/B0/37/CgoB5l3nFSOAFOkbAABvJKvhTKk938.png)

最后，我们用一张全景图来描述 ForkJoinPool 线程池的内部结构，你可以看到 ForkJoinPool 线程池和其他线程池很多地方都是一样的，但重点区别在于它每个线程都有一个自己的双端队列来存储分裂出来的子任务。ForkJoinPool 非常适合用于递归的场景，例如树的遍历、最优路径搜索等场景。

##### 线程池常用的阻塞队列有哪些？

线程池的内部结构

![img](https://s0.lgstatic.com/i/image3/M01/54/39/CgpOIF3nUryAHhreAAA4T0DXzFI487.png)

线程池的内部结构主要由四部分组成，如图所示。
第一部分是线程池管理器，它主要负责管理线程池的创建、销毁、添加任务等管理操作，它是整个线程池的管家。

第二部分是工作线程，也就是图中的线程 t0~t9，这些线程勤勤恳恳地从任务队列中获取任务并执行。

第三部分是任务队列，作为一种缓冲机制，线程池会把当下没有处理的任务放入任务队列中，由于多线程同时从任务队列中获取任务是并发场景，此时就需要任务队列满足线程安全的要求，所以线程池中任务队列采用 BlockingQueue 来保障线程安全。

第四部分是任务，任务要求实现统一的接口，以便工作线程可以处理和执行。

###### 阻塞队列

<img src="https://s0.lgstatic.com/i/image3/M01/54/39/Cgq2xl3nUryAJBkpAAA0_WFSrB8184.png" alt="img" style="zoom:75%;" />

线程池中的这四个主要组成部分最值得我们关注的就是阻塞队列了，如表格所示，不同的线程池会选用不同的阻塞队列。

表格左侧是线程池，右侧为它们对应的阻塞队列，你可以看到 5 种线程池对应了 3 种阻塞队列，我们接下来对它们进行逐一的介绍。

###### 		LinkedBlockingQueue  

对于FixedThreadPool和SingleThredExecutor而言，它们使用的是容量为Integer.MAX_VALUE的LinkedBlockingQueue，可以认为是无界队列。由于FixedThreadPool的容量是固定的，所以没有办法增加特别多的线程来处理任务，这是就需要LinkedBlockingQueue这样一个没有容量限制的阻塞队列来存放任务。这里需要注意的是，由于线程池的任务永远不会放满，所以线程池只会创建核心数量的线程，所以最大线程数量对于线程池来说没有意义，因为并不会触发生成多于核心线程数的线程。

###### 		SynchronousQueue      

第二种阻塞队列是 SynchronousQueue，对应的线程池是 CachedThreadPool。线程池 CachedThreadPool 的最大线程数是 Integer 的最大值，可以理解为线程数是可以无限扩展的。CachedThreadPool和上一种线程池恰恰时相反的，FixedThreadPool 的情况是阻塞队列的容量是无限的，而CachedThreadPool的情况是线程数可以无限扩展，CachedThreadPool 线程池并不需要一个任务队列来存储任务，因为一旦有任务被提交就直接转发给线程或者创建新线程来执行，而不需要另外保存它们。

我们自己创建使用 SynchronousQueue 的线程池时，如果不希望任务被拒绝，那么就需要注意设置最大线程数要尽可能大一些，以免发生任务数大于最大线程数时，没办法把任务放到队列中也没有足够线程来执行任务的情况。

###### 		DelayedWorkQueue   

第三种阻塞队列是DelayedWorkQueue，它对应的线程池分别是 ScheduledThreadPool 和 SingleThreadScheduledExecutor，这两种线程池的最大特点就是可以延迟执行任务，比如说一定时间后执行任务或是每隔一定的时间执行一次任务。DelayedWorkQueue的特点是内部元素并不是按照放入的时间顺序，而是会按照延迟的时间长短对任务进行排序，内部采用的是"堆"的数据结构，之所以线程池 ScheduledThreadPool 和 SingleThreadScheduledExecutor 选择 DelayedWorkQueue，是因为它们本身正是基于时间执行任务的，而延迟队列正好可以把任务按照时间进行排序，方便执行。

##### 为什么不应该自动创建线程池？

##### 		FixedThreadPool	

首先我们来看第一种线程池 FixedThreadPool， 它是线程数量固定的线程池，如源码所示，newFixedThreadPool 内部实际还是调用了 ThreadPoolExecutor 构造函数。

```java
public static ExecutorService newFixedThreadPool(int nThreads) { 
    return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
}
```

通过往构造函数中传参，创建了一个核心线程数和最大线程数相等的线程池，它们的数量也就是我们传入的参数，这里的重点是使用的队列是容量没有上限的 LinkedBlockingQueue，如果我们对任务的处理速度比较慢，那么随着请求的增多，队列中堆积的任务也会越来越多，最终大量堆积的任务会占用大量内存，并发生 OOM ，也就是OutOfMemoryError，这几乎会影响到整个程序，会造成很严重的后果。

##### 		SingleThreadExecutor

第二种线程池是 SingleThreadExecutor，我们来分析下创建它的源码。

```java
public static ExecutorService newSingleThreadExecutor() { 
    return new FinalizableDelegatedExecutorService (new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()));
}
```

你可以看出，newSingleThreadExecutor 和 newFixedThreadPool 的原理是一样的，只不过把核心线程数和最大线程数都直接设置成了 1，但是任务队列仍是无界的 LinkedBlockingQueue，所以也会导致同样的问题，也就是当任务堆积时，可能会占用大量的内存并导致 OOM。

##### 		CachedThreadPool

第三种线程池是 CachedThreadPool，创建它的源码下所示。

```
public static ExecutorService newCachedThreadPool() { 
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
}
```

这里的 CachedThreadPool 和前面两种线程池不一样的地方在于任务队列使用的是 SynchronousQueue，SynchronousQueue 本身并不存储任务，而是对任务直接进行转发，这本身是没有问题的，但你会发现构造函数的第二个参数被设置成了 Integer.MAX_VALUE，这个参数的含义是最大线程数，所以由于 CachedThreadPool 并不限制线程的数量，当任务数量特别多的时候，就可能会导致创建非常多的线程，最终超过了操作系统的上限而无法创建新线程，或者导致内存不足。

##### 		ScheduledThreadPool 和 SingleThreadScheduledExecutor

第四种线程池 ScheduledThreadPool 和第五种线程池 SingleThreadScheduledExecutor 的原理是一样的，创建 ScheduledThreadPool 的源码如下所示。

```java
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) { 
    return new ScheduledThreadPoolExecutor(corePoolSize);
}
```

而这里的 ScheduledThreadPoolExecutor 是 ThreadPoolExecutor 的子类，调用的它的构造方法如下所示。

```java
public ScheduledThreadPoolExecutor(int corePoolSize) { 
    super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue());
}
```

我们通过源码可以看出，它采用的任务队列是 DelayedWorkQueue，这是一个延迟队列，同时也是一个无界队列，所以和 LinkedBlockingQueue 一样，如果队列中存放过的任务，就可能导致 OOM。