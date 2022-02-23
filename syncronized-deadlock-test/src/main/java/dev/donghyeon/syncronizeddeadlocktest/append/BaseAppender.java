package dev.donghyeon.syncronizeddeadlocktest.append;

public class BaseAppender {
    
    public synchronized void doAAppend() {
        System.out.println("base appender");
    }
}
