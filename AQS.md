##### AQS的一些内容

1.Semaphore（信号量又被称为信号灯，在多线程下用于协调各个线程，以保证它们能够正确合理的使用资源。

acquire():线程通过它获取一个许可，然后对共享资源进行操作，如果许可集已经分配完了，那么线程进入等待状态，直到其它线程释放许可才可以再次获取许可。

release():线程释放一个许可，许可将被归还给Semphore。

2.CyclicBarrier：它类似于CountDownLatch，它能阻塞一组线程直到某个事件发生。CyclicBarrier与CountDownLatch的区别关键在于，所有事件必须同时到达栅栏处，才能继续进行。CountDownLatch用于等待事件，CyclicBarrier用于等待其它线程。

await():调用await方法的线程告诉CyclicBarrier它已经到达同步点，然后当前线程被阻塞。CyclicBarrier同样提供带超时时间和不带超时时间的await方法。

3.CountDownLatch：通过一个计数器实现，计数器的初始值是当前线程的数量。每当一个线程执行完毕后，技术器就执行-1操作，当计数器值为0时，所以线程都执行完毕。

await():调用了await方法的线程会被挂起，它会等待count为0才会继续执行，await(long timeout,TimeUnit unit)zhe'ge这个带超时时间的方法表示当等待一定时间后count还没为0就继续执行。

countdown():对count的值进行减1操作。

4.ReentrantLock:https://mp.weixin.qq.com/s/dM6ITD2ecRdgsriQYbtxfQ