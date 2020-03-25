package com.example.leetcode.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 */
public class Consumer<T> {
    Queue<T> queue;
    ReentrantLock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition empty = lock.newCondition();
    int size;

    public Consumer(int _size) {
        queue = new LinkedList<>();
        size = _size;
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            if(queue.isEmpty()) {
                empty.await();
            }
            T item = queue.poll();
            full.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public void put(T t) throws InterruptedException{
        lock.lock();
        try {
            if(queue.size() == size) {
                full.await();
            }
            queue.offer(t);
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
