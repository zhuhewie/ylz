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
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.DatePickerEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-13.
 * 时间选择的dialog
 * 预约挂号功能中,选择科室之后,科室预约医生列表界面的弹出框
 */
public class ChooseDateDialog extends SuperDialogFragment {


    @BindView(R.id.start_date)
    TextView textStartDate;
    @BindView(R.id.end_date)
    TextView textEndDate;
    @BindView(R.id.text_cancle)
    Button textCancle;
    @BindView(R.id.text_ok)
    Button textOk;


    public static ChooseDateDialog newInstance(String title) {
        ChooseDateDialog frag = new ChooseDateDialog();
        Bundle args = new Bundle();
        //args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_choose_date, container);
        ButterKnife.bind(this, view);
        addClick();
        return view;
    }

    private void addClick() {
        RxView.clicks(textCancle)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });
        RxView.clicks(textOk)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //发送数据到医生列表界面
                        dismiss();
                    }
                });
        //开始时间选择
        RxView.clicks(textStartDate)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        DatePickerDialog.newInstance(GeneralConfig.CHOOSE_START_DATE)
                                .show(getFragmentManager(),"startDate");
                    }
                });
        //结束时间选择
        RxView.clicks(textEndDate)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        DatePickerDialog.newInstance(GeneralConfig.CHOOSE_END_DATE)
                                .show(getFragmentManager(),"endDate");
                    }
                });
    }


    @Subscribe
    public void onEvent(DatePickerEvent event) {
        //Snackbar.make(textOk, "来自dialog的点击响应", 3000).show();
        switch (event.dateType){
            case GeneralConfig.CHOOSE_START_DATE:
                textStartDate.setText(Uitls.format.format(event.chooseDate));
                break;
            case GeneralConfig.CHOOSE_END_DATE:
                textEndDate.setText(Uitls.format.format(event.chooseDate));
                break;
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }



}
