package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;

/**
 * Created by Administrator on 2016-10-9.
 * <p/>
 * 在线诊室功能中创建就诊卡界面
 */
public class CreatePatientCardDialog extends SuperDialogFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.btn_new)
    Button btnNew;

    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.tiet_hospital)
    TextInputEditText tietHospital;
    @BindView(R.id.til_hospital)
    TextInputLayout tilHospital;
    @BindView(R.id.tiet_card_name)
    TextInputEditText tietCardName;
    @BindView(R.id.til_card_name)
    TextInputLayout tilCardName;
    @BindView(R.id.tiet_card_sex)
    TextInputEditText tietCardSex;
    @BindView(R.id.til_card_sex)
    TextInputLayout tilCardSex;
    @BindView(R.id.tiet_card_type)
    TextInputEditText tietCardType;
    @BindView(R.id.til_card_type)
    TextInputLayout tilCardType;
    @BindView(R.id.tiet_card_idcard)
    TextInputEditText tietCardIdcard;
    @BindView(R.id.til_card_idcard)
    TextInputLayout tilCardIdcard;
    @BindView(R.id.tiet_age)
    TextInputEditText tietAge;
    @BindView(R.id.til_age)
    TextInputLayout tilAge;
    @BindView(R.id.tiet_phone_number)
    TextInputEditText tietPhoneNumber;
    @BindView(R.id.til_img_code)
    TextInputLayout tilPhoneNumber;
    @BindView(R.id.tiet_card_number)
    TextInputEditText tietCardNumber;
    @BindView(R.id.til_card_number)
    TextInputLayout tilCardNumber;


    public static CreatePatientCardDialog newInstance() {
        CreatePatientCardDialog frag = new CreatePatientCardDialog();
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
        View view = inflater.inflate(R.layout.dialog_create_patient_card, container);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        addData();

        return view;
    }

    private void addData() {

    }

    private void addClick() {
        RxView.clicks(imgBack)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });
        RxView.clicks(btnNew)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //可能要传递数据
                        dismiss();
                    }
                });

    }

    /**
     * 初始化界面
     */
    private void initView() {
        textViewName.setText("新增就诊卡");
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.BOTTOM);
        super.onResume();
    }
}
