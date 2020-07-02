import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program:untitled
 * @description:
 * @author:Juwenchao
 * @date:2020-07-02 18:18:13
 */

/**
 * 读写锁的获取规则
 * 我们在使用读写锁时遵守下面的获取规则：
 * 如果有一个线程已经占用了读锁，则此时其他线程如果要申请读锁，可以申请成功。
 * 如果有一个线程已经占用了读锁，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁，因为读写不能同时操作。
 * 如果有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，都必须等待之前的线程释放写锁，同样也因为读写不能同时，并且两个线程不应该同时写。
 * 所以我们用一句话总结：要么是一个或多个线程同时有读锁，要么是一个线程有写锁，但是两者不会同时出现。也可以总结为：读读共享、其他都互斥（写写互斥、读写互斥、写读互斥）。
 */
public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static final ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
       /* Lock lock = new ReentrantLock();
        if (lock.tryLock()) {
            Condition condition = lock.newCondition();
            try {
                condition.await();
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }
*/
        new Thread(() -> read()).start();
        new Thread(() -> read()).start();
        new Thread(() -> write()).start();
        new Thread(() -> write()).start();


    }

    public static void read() {
        readLock.lock();
        System.out.println(Thread.currentThread().getName() + "得到读锁，正在读取");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读锁");
        }
    }

    public static void write() {
        writeLock.lock();
        System.out.println(Thread.currentThread().getName() + "得到写锁，正在写入");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
        }
    }
}
