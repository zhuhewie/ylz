package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserId;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.PatientCardDialogEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-22.
 * 选择就诊卡的dialog
 */
public class ChoosePatientCardDialog extends SuperDialogFragment {
    @BindView(R.id.other_patient)
    Button otherPatient;
    @BindView(R.id.dismiss)
    Button dismiss;
    @BindView(R.id.parent_card)
    LinearLayout layoutParentCard;

    private List<PatientCardBean> listPatientCard;

    public static ChoosePatientCardDialog newInstance() {
        ChoosePatientCardDialog frag = new ChoosePatientCardDialog();
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
        View view = inflater.inflate(R.layout.dialog_choose_patient_card, container,false);
        ButterKnife.bind(this, view);
        addClick();
        addData();

        return view;
    }

    //界面的点击事件
    private void addClick() {
        //取消按钮,结束当前界面
        RxView.clicks(dismiss)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });

        //其他就诊卡的点击事件
        RxView.clicks(otherPatient)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //结束当前dialog,然后返回数据给原界面
                        EventBus.getDefault().post(new PatientCardDialogEvent(null));
                        dismiss();
                    }
                });

    }

    /**
     * 获取登陆者的就诊卡
     */
    private void addData() {
        if (!Uitls.isEmptyClazz(UserManager.getInstance().getCurrentUser()) && !TextUtils.isEmpty(UserManager.getInstance().getCurrentUser().getUserId())) {
            //联网加载就诊卡数据
            HttpMethods.getInstance().getPatientCard(new Subscriber<List<PatientCardBean>>() {
                @Override
                public void onCompleted() {
                    addView();
                }

                @Override
                public void onError(Throwable e) {
                    Snackbar.make(otherPatient, "获取数据失败", 2000).show();
                    Log.i("就诊卡获取数据失败", e.toString() + "");

                }

                @Override
                public void onNext(List<PatientCardBean> patientCard) {
                    listPatientCard = patientCard;
                    Log.i("就诊卡", listPatientCard.toString() + "");
                }
            }, new UserId(UserManager.getInstance().getCurrentUser().getUserId()));
        }
    }

    /**
     * 动态加载就诊卡视图
     */
    private void addView() {
        for (int i = 0; i < listPatientCard.size(); i++) {
//            LinearLayout layout = new LinearLayout(getContext());
//            layout.setOrientation(LinearLayout.VERTICAL);
//            LinearLayout.LayoutParams param=new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
            Button button = new Button(getContext());
            final PatientCardBean patientCard = listPatientCard.get(i);
            button.setText(patientCard.getPatName());
            button.setBackgroundResource(R.drawable.touch_bg);
            layoutParentCard.addView(button);
            RxView.clicks(button)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            //传递数据到主界面
                            EventBus.getDefault().post(new PatientCardDialogEvent(patientCard));
                            dismiss();
                        }
                    });

        }
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);
        super.onResume();
    }
}
