package net.jakubkorab.rx;

import java.util.concurrent.CountDownLatch;

/**
 * @author jkorab
 */
public class LatchedObserver extends LoggingObserver {

    private final CountDownLatch latch;

    public LatchedObserver(String name, CountDownLatch latch) {
        super(name);
        this.latch = latch;
    }

    @Override
    public void onNext(String string) {
        super.onNext(string);
        latch.countDown();
    }
}
