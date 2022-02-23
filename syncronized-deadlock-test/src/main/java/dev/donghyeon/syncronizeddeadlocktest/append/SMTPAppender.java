package dev.donghyeon.syncronizeddeadlocktest.append;

public class SMTPAppender extends BaseAppender{
    
    @Override
    public synchronized void doAAppend() {
        super.doAAppend();
        sendMail();
    }
    
    private synchronized void sendMail() {
        System.out.println("Thread is going to sleep");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send Mail");
    }
}
