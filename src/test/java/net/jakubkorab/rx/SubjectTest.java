package net.jakubkorab.rx;

import org.junit.Test;
import static org.mockito.Mockito.*;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.util.functions.Action1;

/**
 * @author jkorab
 */
public class SubjectTest {
    @Test
    public void testPublishSubject() {
        Action1<String> mockAction1 = mock(Action1.class);
        Action1<String> mockAction2 = mock(Action1.class);

        // PublishSubject is the equivalent of Subject in .Net
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(mockAction1);
        publishSubject.onNext("a");
        publishSubject.onNext("b");
        publishSubject.subscribe(mockAction2);
        publishSubject.onNext("c");

        verify(mockAction1).call("a");
        verify(mockAction1).call("b");
        verify(mockAction1).call("c");

        verify(mockAction2).call("c");
    }

    @Test
    public void testReplaySubject() {
        Action1<String> mockAction1 = mock(Action1.class);
        Action1<String> mockAction2 = mock(Action1.class);

        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("a");
        replaySubject.subscribe(mockAction1);
        replaySubject.onNext("b");
        replaySubject.subscribe(mockAction2);
        replaySubject.onNext("c");

        verify(mockAction1).call("a");
        verify(mockAction1).call("b");
        verify(mockAction1).call("c");

        verify(mockAction2).call("b");
        verify(mockAction2).call("c");
    }

    @Test
    public void testReplaySubject_bySize() {
        Action1<String> mockAction1 = mock(Action1.class);
        Action1<String> mockAction2 = mock(Action1.class);

        ReplaySubject<String> replaySubject = ReplaySubject.create(2); // increase historical capacity
        replaySubject.onNext("a");
        replaySubject.subscribe(mockAction1);
        replaySubject.onNext("b");
        replaySubject.subscribe(mockAction2);
        replaySubject.onNext("c");

        verify(mockAction1).call("a");
        verify(mockAction1).call("b");
        verify(mockAction1).call("c");

        verify(mockAction2).call("a");
        verify(mockAction2).call("b");
        verify(mockAction2).call("c");
    }

}
