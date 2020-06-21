package com.example.leetcode.thread;

/**
 * 循环打印abc字符串
 */
public class ABCPrinter {
    public static void main(String[] args) {
        String str = "abc";
        int n = 20;

        for (int i = 0; i < str.length(); i++) {
            new Thread(new ABCRunnable(str.charAt(i), n, i, str.length())).start();
        }
    }

    public static class ABCRunnable implements Runnable {
        static final Object lock = new Object();
        static volatile int state = 0;

        private char printChar;
        private int repeatTimes;
        private int thisState;
        private int stateCount;
        public ABCRunnable(char aChar, int n, int state, int count) {
            printChar = aChar;
            repeatTimes = n;
            thisState = state;
            stateCount = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < repeatTimes; i++) {
                synchronized (lock) {
                    while (state != thisState) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(printChar);
                    int newState = (state + 1) % stateCount;
                    state = newState;
                    lock.notifyAll();
                }
            }
        }
    }
}
