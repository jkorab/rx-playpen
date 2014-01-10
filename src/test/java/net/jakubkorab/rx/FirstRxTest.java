package net.jakubkorab.rx;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.util.functions.Action1;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * @author jkorab
 */
public class FirstRxTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testObservableArray() throws InterruptedException {
        String[] names = new String[] {"Jake", "Alex"};
        final CountDownLatch latch = new CountDownLatch(names.length);

        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
               log.info("Hello {}", name);
               latch.countDown();
            }
        });

        latch.await();
        assertTrue(true);
    }

    @Test
    public void testObservableArray_withObserver() throws InterruptedException {
        String[] names = new String[] {"Jake", "Alex"};
        final CountDownLatch latch = new CountDownLatch(names.length);

        LatchedObserver observer = new LatchedObserver("one", latch);
        Observable.from(names).subscribe(observer);

        latch.await();
        assertTrue(true);
    }
}
