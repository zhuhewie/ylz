package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.DoctorAppDateAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.JsonParam;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AppTimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DoctorAppMessageBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.subscriber.SubscriberOnNextLisnter;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-12.
 * <p/>
 * 医生预约时间选着界面
 */
public class DoctorAppDateFragment extends SuperFragment {
    private static final String ARG_DOCTOR = "doctor";
    private static final String ARG_HOSPITAL = "hospital";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_me)
    ImageView imgDoctor;
    @BindView(R.id.text_doctor_name)
    TextView textDoctorName;
    @BindView(R.id.text_doctor_lv)
    TextView textDoctorLv;
    @BindView(R.id.text_doctor_hos_depa)
    TextView textDoctorHosDepa;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.text_app_hospital_department)
    TextView textAppHospitalDepartment;
//    @BindView(R.id.layout_hospital_department)
//    LinearLayout layoutHospitalDepartment;
    @BindView(R.id.layout_choose_date)
    LinearLayout layoutChooseDate;
    @BindView(R.id.recy_app_time)
    RecyclerView recyApptime;
    @BindView(R.id.layout_hospital_department)
    LinearLayout layoutHospitalDepartment;
    @BindView(R.id.text_add_date_title)
    TextView textAddDateTitle;
    @BindView(R.id.text_add_am_title)
    TextView textAddAmTitle;
    @BindView(R.id.text_add_pm_title)
    TextView textAddPmTitle;

    private DoctorBean doctor;
    private Hopital hopital;
    private SubscriberOnNextLisnter<List<Object>> listDoctorOnNext;
    private List<DoctorAppMessageBean> listDoctor;
    private List<Object> listAll = new ArrayList<>();
    private DoctorAppDateAdapter appDateAdapter;


    public static DoctorAppDateFragment newInstance(DoctorBean doctor, Hopital hospital) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_DOCTOR, doctor);
        args.putParcelable(ARG_HOSPITAL, hospital);
        DoctorAppDateFragment fragment = new DoctorAppDateFragment();
        fragment.setArguments(args);
        //fragment.setTargetFragment(f,300);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            doctor = bundle.getParcelable(ARG_DOCTOR);
            hopital = bundle.getParcelable(ARG_HOSPITAL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sadd_data, container, false);
        ButterKnife.bind(this, v);
        initView();
        addData();
        addClick();
        return v;
    }

    /**
     * 联网获取医生的排班数据
     */
    private void addData() {
        HttpMethods.getInstance().getAppDoctor2(getActivity(), listDoctorOnNext,
                new JsonParam<>(new AppTimeBean(Uitls.getTime(0), doctor.DeptCode, doctor.DoctorCode, Uitls.getTime(30))));

    }

    private void initView() {
        textDoctorName.setText(doctor.DoctorName); //医生姓名
        textDoctorLv.setText(doctor.ClinicResponceName); //医生等级
        //医院和科室
        textDoctorHosDepa.setText(hopital.getOrgName() + "\t" + doctor.DeptName);
        textAppHospitalDepartment.setText(hopital.getOrgName() + "\t" + doctor.DeptName);
        //体检套餐列表
        GridLayoutManager glManager = new GridLayoutManager(getContext(),3);
        glManager.setOrientation(OrientationHelper.HORIZONTAL); //设置垂直方向滑动的列表
        recyApptime.setLayoutManager(glManager);

        appDateAdapter = new DoctorAppDateAdapter();
        recyApptime.setAdapter(appDateAdapter);

        dataListener();
    }

    private void addClick() {
        //顶部返回按钮的点击事件
        RxView.clicks(imgBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });

        //显示或隐藏 预约信息列表
        RxView.clicks(layoutHospitalDepartment)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        int showView = layoutChooseDate.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
                        layoutChooseDate.setVisibility(showView);
                    }
                });

        //医生根据日期排班列表的条目点击事件
        appDateAdapter.setClickListener(new DoctorAppDateAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switchFatherContent(DoctorAppDateFragment.this,
                        AppOutpatientFragment.newInstance((DoctorAppMessageBean) listAll.get(position),
                                hopital,
                                position%3)); //position % 3 等于1 :上午,等于2:下午

            }
        });
    }


    public void dataListener() {
        listDoctorOnNext = new SubscriberOnNextLisnter<List<Object>>() {
            @Override
            public void onNext(List<Object> list) {
                listAll.clear();
                listAll.addAll(list);
                appDateAdapter.setData(listAll);
                appDateAdapter.notifyDataSetChanged();
                Log.i("请求的医生排班信息", list.toString() + "");
            }
        };

    }
}
