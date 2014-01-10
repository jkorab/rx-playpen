package net.jakubkorab.rx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observer;

/**
 * @author jkorab
 */
public class LoggingObserver implements Observer<String> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String name;

    public LoggingObserver(String name) {
        this.name = name;
    }

    @Override
    public void onCompleted() {
        log.info("{} completed", name);
    }

    @Override
    public void onError(Throwable e) {
        log.info("{} error: {}", name, e.toString());
    }

    @Override
    public void onNext(String string) {
        log.info("{} next: {}", name, string);
    }
}
