package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.StartTwoEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult.ChoosePatientCardFragment;

/**
 * Created by Administrator on 2016-10-9.
 * 注意事项界面
 */
public class AttentionDialog extends SuperDialogFragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.cb_agree)
    CheckBox cbAgree;
    @BindView(R.id.btn_ok)
    Button btnOk;

    private boolean okCanClick = false;
    public static AttentionDialog newInstance() {
        AttentionDialog fragment = new AttentionDialog();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View v = inflater.inflate(R.layout.attention_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();
        return v;
    }

    private void initView() {
        textViewName.setText("注意事项");
        btnOk.setClickable(false);

    }

    private void addClick() {
        //返回上一级
        RxView.clicks(imgBack)
                .throttleFirst(700, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //监听checkbox选中的状态
        RxCompoundButton.checkedChanges(cbAgree)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        btnOk.setClickable(aBoolean);
                        okCanClick = aBoolean;
                        btnOk.setBackgroundResource(aBoolean ? R.drawable.touch_btn_selector : R.color.colorButtonClick);
                    }
                });
        //确定点击事件
        RxView.clicks(btnOk)
                .throttleFirst(700, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (okCanClick) {
                            dismiss();
                            EventBus.getDefault().post(new StartTwoEvent(ChoosePatientCardFragment.newInstance()));
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //window.setGravity(Gravity.CENTER);
        super.onResume();
    }

}
