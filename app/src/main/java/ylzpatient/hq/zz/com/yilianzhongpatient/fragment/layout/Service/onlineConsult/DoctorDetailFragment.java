package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.OnlineDoctorBean;

/**
 * Created by Administrator on 2016-10-8.
 */
public class DoctorDetailFragment extends SuperFragment {

    private static final String ARG_DOCTOR = "arg_doctor";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.text_doctor_name)
    TextView textDoctorName;
    @BindView(R.id.text_doctor_lv)
    TextView textDoctorLv;
    @BindView(R.id.text_doctor_hos_depa)
    TextView textDoctorHosDepa;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.tv_doctor_hospital)
    TextView tvDoctorHospital;
    @BindView(R.id.tv_doctor_good)
    TextView tvDoctorGood;
    @BindView(R.id.tv_doctor_more_good)
    TextView tvDoctorMoreGood;
    @BindView(R.id.tv_doctor_introduction)
    TextView tvDoctorIntroduction;
    private OnlineDoctorBean doctor;

    public static DoctorDetailFragment newInstance(OnlineDoctorBean doctor) {
        DoctorDetailFragment fragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_DOCTOR, doctor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            doctor = bundle.getParcelable(ARG_DOCTOR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.od_detail_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();
        loadingData();//模拟添加数据
        return v;
    }

    /**
     * 初始化界面数据
     */
    private void initView() {
        textDoctorName.setText(doctor.getDoctorName());
        textDoctorLv.setText(doctor.getDoctorTitle());
        textDoctorHosDepa.setText(doctor.getDepartment());
        tvDoctorHospital.setText(doctor.getHospitalName());
        tvDoctorGood.setText(doctor.getBeGoodAt());
        tvDoctorMoreGood.setText(doctor.getBeGoodAt());
    }

    /**
     * 点击事件
     */
    private void addClick() {
        //返回按钮
        RxView.clicks(imgBack)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
    }

    /**
     * 加载数据
     */
    private void loadingData() {

    }


}
