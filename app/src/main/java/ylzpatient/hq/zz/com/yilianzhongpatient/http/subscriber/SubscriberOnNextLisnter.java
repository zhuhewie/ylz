package ylzpatient.hq.zz.com.yilianzhongpatient.http.subscriber;

/**
 * Created by Kacent on 2016/8/29.
 */

public interface SubscriberOnNextLisnter<T> {
    void onNext(T t);
}
