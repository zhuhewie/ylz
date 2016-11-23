package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.SwitchFragmentEvent;

/**
 * Created by Administrator on 2016-10-12.
 * 二次确认是否进入诊室的dialog
 */
public class InClinicDialog extends SuperDialogFragment {

    @BindView(R.id.tv_surplus_time)
    TextView tvSurplusTime;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnIn;

    public static InClinicDialog newInstance() {
        InClinicDialog frag = new InClinicDialog();
        //Bundle args = new Bundle();
        //args.putString("title", title);
        //frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_inclinic, container);
        ButterKnife.bind(this, view);
        addClick();
        return view;
    }

    private void addClick() {
        //取消按钮
        RxView.clicks(btnCancel)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });
        RxView.clicks(btnIn)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //进入诊室界面
                        EventBus.getDefault().post(new SwitchFragmentEvent(GeneralConfig.CLINIC_FRAGMENT));
                        dismiss();
                    }
                });
    }

    @Override
    public void onResume() {
        //设置dialog的宽度和高度
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // 设置宽度为屏幕宽度的80%,高度包裹内容
        window.setLayout((int) (size.x * 0.8), WindowManager.LayoutParams.WRAP_CONTENT);
        //位于屏幕中间
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }
}
