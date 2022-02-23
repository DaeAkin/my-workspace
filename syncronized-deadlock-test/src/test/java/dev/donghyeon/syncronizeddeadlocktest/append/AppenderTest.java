package dev.donghyeon.syncronizeddeadlocktest.append;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppenderTest {
    
    
    @BeforeEach
    void setUp() {
    }
    
    @Test
    void test() throws InterruptedException {
        SMTPAppender smtpAppender = new SMTPAppender();
        int threadNum = 5;

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);

        for(int i = 0; i < threadNum; i++) {
            executorService.submit(() -> smtpAppender.doAAppend());
        }
        
        Thread.sleep(10000);
    }
}
