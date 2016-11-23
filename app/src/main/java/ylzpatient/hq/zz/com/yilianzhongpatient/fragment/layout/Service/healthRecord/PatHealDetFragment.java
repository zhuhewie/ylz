package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.healthRecord;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.ScollViewPagerAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthPatientBean;

/**
 * Created by Administrator on 2016-11-14.
 * 健康档案详情界面,
 */

public class PatHealDetFragment extends SuperFragment {
    private static final String ARG_PATIENT = "patient";

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.tl_health_detial)
    TabLayout tlHealthDetial;
    @BindView(R.id.vp_health_detial)
    ViewPager vpHealthDetial;

    private List<Fragment> fragmentList;
    private List<String> listTitleName;
    private HealthPatientBean patient;

    public static PatHealDetFragment newInstance(HealthPatientBean patient) {
        PatHealDetFragment fragment = new PatHealDetFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PATIENT, patient);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patient = getArguments().getParcelable(ARG_PATIENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pat_hea_detial, container, false);
        ButterKnife.bind(this, view);
//        EventBus.getDefault().register(this);

        initView();

        addClick();
        return view;
    }

    private void addClick() {
        //顶部返回图标的点击事件
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
    }

    private void initView() {
        textViewName.setText("健康档案");

        fragmentList = new ArrayList<>();
        listTitleName = new ArrayList<>();
        fragmentList.add(BasicFragment.newInstance());
        listTitleName.add("基本信息");
        fragmentList.add(ClinicalFragment.newInstance(patient));
        listTitleName.add("临床档案");
        fragmentList.add(HealthMessFragment.newInstance(patient));
        listTitleName.add("健康信息");
        ScollViewPagerAdapter serAppointAdapter = new ScollViewPagerAdapter(
                getActivity().getSupportFragmentManager(), fragmentList, listTitleName);
        vpHealthDetial.setAdapter(serAppointAdapter);

        //设置tab滚动条的颜色
        tlHealthDetial.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        tlHealthDetial.setupWithViewPager(vpHealthDetial); //关联tablayout和viewpager
    }
}
