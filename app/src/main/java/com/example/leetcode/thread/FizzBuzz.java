package com.example.leetcode.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1195. 交替打印字符串
 */
public class FizzBuzz {
    /**
     * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
     *
     * 如果这个数字可以被 3 整除，输出 "fizz"。
     * 如果这个数字可以被 5 整除，输出 "buzz"。
     * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private int n;
    //也可以使用volatile
    private AtomicInteger x;
    private final Object lock;

    public FizzBuzz(int n) {
        this.n = n;
        x = new AtomicInteger(1);
        lock = new Object();
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (x.get() <= n) {
            synchronized (lock) {
                int num = x.get();
                if(num % 3 == 0 && num % 5 != 0) {
                    printFizz.run();
                    x.incrementAndGet();
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (x.get() <= n) {
            synchronized (lock) {
                int num = x.get();
                if(num % 3 != 0 && num % 5 == 0) {
                    printBuzz.run();
                    x.incrementAndGet();
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (x.get() <= n) {
            synchronized (lock) {
                int num = x.get();
                if(num % 15 == 0) {
                    printFizzBuzz.run();
                    x.incrementAndGet();
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (x.get() <= n) {
            synchronized (lock) {
                int num = x.get();
                if(num % 3 != 0 && num % 5 != 0) {
                    printNumber.accept(num);
                    x.incrementAndGet();
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }
}
