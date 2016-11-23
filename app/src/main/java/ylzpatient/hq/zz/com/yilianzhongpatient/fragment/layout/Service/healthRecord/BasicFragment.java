package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.healthRecord;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestCountResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthBasicBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 健康档案的基本信息界面
 */
public class BasicFragment extends SuperFragment {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_weight)
    TextView tvWeight;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_blood_type)
    TextView tvBloodType;
    @BindView(R.id.tv_allergy)
    TextView tvAllergy;
    @BindView(R.id.tv_family_history)
    TextView tvFamilyHistory;
    @BindView(R.id.tv_drink)
    TextView tvDrink;
    @BindView(R.id.tv_smoke)
    TextView tvSmoke;
    @BindView(R.id.tv_medication)
    TextView tvMedication;


//    private String mParam1;
//    private String mParam2;

    private UserIdRequestBean request;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BasicFragment.
     */
    public static BasicFragment newInstance() {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base_information, container, false);
        ButterKnife.bind(this, view);
        initView();
        loadingData();
        return view;
    }

    /**
     * 初始化界面数据
     */
    private void initView() {
        request = new UserIdRequestBean();

        request.setUserId(UserManager.getInstance().getCurrentUser().getUserId());

    }

    /**
     * 网络加载数据
     */
    private void loadingData() {
        HttpMethods.getInstance().getReportBasic(new Subscriber<ResquestCountResult<HealthBasicBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(GeneralConfig.LOG_TAG, "获取患者健康档案列表失败:" + e.toString());

            }

            @Override
            public void onNext(ResquestCountResult<HealthBasicBean> BasicResult) {
                Log.i(GeneralConfig.LOG_TAG, "获取患者健康档案列表成功:" + BasicResult.toString());
                setData(BasicResult.ResultData);
            }
        }, request);
    }

    /**
     * 显示界面数据
     */
    private void setData(HealthBasicBean basic) {
        tvName.setText(basic.getName());
        tvSex.setText(basic.getSex());
        tvAge.setText(basic.getAge()+"岁");
        tvWeight.setText(basic.getWeight()+"KG");
        tvBloodType.setText(basic.getBlood());
        tvAllergy.setText(basic.getAllergies());
        tvFamilyHistory.setText(basic.getFamily());
        tvDrink.setText(basic.getDrinking());
        tvSmoke.setText(basic.getSmoking());
        tvMedication.setText(basic.getMedical());
    }

}
