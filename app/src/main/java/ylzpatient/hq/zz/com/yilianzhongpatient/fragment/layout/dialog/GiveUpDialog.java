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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxRadioGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.BackEvent;

import static ylzpatient.hq.zz.com.yilianzhongpatient.R.id.rb_reason1;

/**
 * Created by Administrator on 2016-10-17.
 * 放弃诊室的二次确认界面
 */

public class GiveUpDialog extends SuperDialogFragment {


    @BindView(R.id.rg_giveup_reason)
    RadioGroup rgGiveupReason;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_ok)
    Button btnOk;


    @BindView(R.id.et_other)
    EditText etOther;
    @BindView(rb_reason1)
    RadioButton rbReason1;
    @BindView(R.id.rb_reason2)
    RadioButton rbReason2;
    @BindView(R.id.rb_reason3)
    RadioButton rbReason3;
    @BindView(R.id.rb_reason4)
    RadioButton rbReason4;

    private String backReason = "";

    public static GiveUpDialog newInstance() {
        GiveUpDialog fragment = new GiveUpDialog();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View v = inflater.inflate(R.layout.dialog_giveup, container);
        ButterKnife.bind(this, v);
        initView();

        addClick();
        return v;
    }

    private void addClick() {
        //确认按钮的点击
        RxView.clicks(btnOk)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //11:结束诊室界面的标签
                        EventBus.getDefault().post(new BackEvent(11,backReason));
                        dismiss();
                    }
                });
        //取消按钮的点击
        RxView.clicks(btnCancel)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });
        //回退原因选择
        RxRadioGroup.checkedChanges(rgGiveupReason)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
//                        int i = integer;
                        if (integer == rbReason1.getId()) {
                            backReason = rbReason1.getText().toString();
                        } else if (integer == rbReason2.getId()) {
                            backReason = rbReason2.getText().toString();
                        } else if (integer == rbReason3.getId()) {
                            backReason = rbReason3.getText().toString();
                        } else if (integer == rbReason4.getId()) {
                            backReason = rbReason4.getText().toString();
                        }
                    }
                });


    }

    private void initView() {

    }


    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // 设置宽度为屏幕宽度的80%,高度包裹内容
        window.setLayout((int) (size.x * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);

        //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

}
