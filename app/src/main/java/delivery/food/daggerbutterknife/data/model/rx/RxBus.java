package delivery.food.daggerbutterknife.data.model.rx;

import delivery.food.daggerbutterknife.data.model.MessageEvent;
import io.reactivex.subjects.BehaviorSubject;

public final class RxBus {
    private static final BehaviorSubject<MessageEvent> behaviorSubject=BehaviorSubject.create();

    public static BehaviorSubject<MessageEvent> getBehaviorSubject(){
        return behaviorSubject;
    }
}
