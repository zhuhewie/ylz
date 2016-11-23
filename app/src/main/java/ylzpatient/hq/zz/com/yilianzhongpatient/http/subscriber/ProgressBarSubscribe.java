package ylzpatient.hq.zz.com.yilianzhongpatient.http.subscriber;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.net.ConnectException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.view.ProgressView;

/**
 * Created by Kacent on 2016/8/29.
 */

public class ProgressBarSubscribe<T> extends Subscriber<T> {
    private ProgressView progressView;
    private SubscriberOnNextLisnter onNextLisnter;
    private FragmentActivity context;

    public ProgressBarSubscribe(SubscriberOnNextLisnter onNextLisnter, FragmentActivity context) {

        this.onNextLisnter = onNextLisnter;
        this.context = context;
        progressView = new ProgressView();
    }

    private void showDialog() {

        progressView.show(context.getSupportFragmentManager(), "dialog");
        progressView.setStyle(DialogFragment.STYLE_NORMAL, R.style.LodingDialog);
    }

    private void dismissDialog() {
        progressView.dismiss();

    }

    @Override
    public void onStart() {
        showDialog();
    }

    @Override
    public void onCompleted() {
        dismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof TimeoutException) {

            //TipUtils.showTips("网络连接超时，请检查网络是否可用");
        } else if (e instanceof ConnectException) {
            //TipUtils.showTips("网络连接失败，请检查网络是否可用");

        } else {
            //TipUtils.showTips("error:" + e.toString());
            Log.i("appError", e.toString());
        }
        dismissDialog();
    }

    @Override
    public void onNext(T t) {
        onNextLisnter.onNext(t);
        dismissDialog();
    }
}
