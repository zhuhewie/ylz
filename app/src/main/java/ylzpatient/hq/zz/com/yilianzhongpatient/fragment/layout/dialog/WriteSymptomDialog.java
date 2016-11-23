package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.WriteSymptomEvent;

/**
 * Created by Administrator on 2016-9-27.
 * 症状描述界面
 */
public class WriteSymptomDialog extends SuperDialogFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.et_symptom)
    EditText etSymptom;
    @BindView(R.id.btn_wriite_ok)
    Button btnWriiteOk;
    @BindView(R.id.ll_symptom)
    LinearLayout llSymptom;

    private InputMethodManager imm;

    public static WriteSymptomDialog newInstance() {
        WriteSymptomDialog frag = new WriteSymptomDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_write_symptom_layout, container);
        ButterKnife.bind(this, view);
        textViewName.setText("症状描述");
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        addClick();
        return view;
    }

    private void addClick() {
        //左上角返回图片的点击事件
        RxView.clicks(imgBack)
                .throttleFirst(700, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });
        //确定按钮的点击事件
        RxView.clicks(btnWriiteOk)
                .throttleFirst(700, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        String s = etSymptom.getText().toString();
                        if (!TextUtils.isEmpty(s)) {
                            EventBus.getDefault().post(new WriteSymptomEvent(s));
                            dismiss();
                        } else {
                            Snackbar.make(btnWriiteOk, "没有输入症状", 3000).show();
                        }
                    }
                });
        //大的输入框点击事件
        RxView.clicks(llSymptom)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //2.调用showSoftInput方法显示软键盘，其中view为聚焦的view组件
                        imm.showSoftInput(etSymptom,InputMethodManager.SHOW_FORCED);
                    }
                });
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        //设置dialog宽高
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setDimAmount(1.0f);//设置透明度
        super.onResume();
    }
}
