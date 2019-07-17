package ink.chengcan.bus;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

public class BackPressRxBus {
    private final FlowableProcessor<Object> _bus;

    private BackPressRxBus() {
        // toSerialized method made bus thread safe
        _bus = PublishProcessor.create().toSerialized();
    }

    public static BackPressRxBus getInstance() {
        return Holder.BUS;
    }

    public void send(Object obj) {
        _bus.onNext(obj);
    }

    /**
     * 根据传递的 eventType 类型返回特定事件类型的被观察者
     */
    public <T> Flowable<T> toFlowable(Class<T> tClass) {
        return _bus.ofType(tClass);
    }

    public Flowable<Object> toFlowable() {
        return _bus;
    }

    public boolean hasSubscribers() {
        return _bus.hasSubscribers();
    }

    private static class Holder {
        private static final BackPressRxBus BUS = new BackPressRxBus();
    }

}
