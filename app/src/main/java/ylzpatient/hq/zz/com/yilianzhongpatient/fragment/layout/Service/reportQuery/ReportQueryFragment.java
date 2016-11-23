package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.reportQuery;

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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.ScollViewPagerAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.StartTwoEvent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ReportQueryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportQueryFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.tl_report)
    TabLayout tlReport;
    @BindView(R.id.vp_report)
    ViewPager vpReport;
    @BindView(R.id.img_function)
    ImageView imgFunction;

    private String mParam1;
    private String mParam2;
    private ScollViewPagerAdapter serAppointAdapter;
    private List<Fragment> fragmentList;
    private List<String> listTitleName;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ReportQueryFragment.
     */
    public static ReportQueryFragment newInstance() {
        ReportQueryFragment fragment = new ReportQueryFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report_query, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        addClick();
        return view;
    }

    private void initView() {
        fragmentList = new ArrayList<>();
        listTitleName = new ArrayList<>();
        textViewName.setText("报告查询");

        fragmentList.add(InspectionFragment.newInstance());
        listTitleName.add("检验报告");
        fragmentList.add(CheckFragment.newInstance());
        listTitleName.add("检查报告");
        serAppointAdapter = new ScollViewPagerAdapter(
                getActivity().getSupportFragmentManager(), fragmentList, listTitleName);
        vpReport.setAdapter(serAppointAdapter);


        //设置tab滚动条的颜色
        tlReport.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        tlReport.setupWithViewPager(vpReport); //关联tablayout和viewpager

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

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onSwitchFragEvent(StartTwoEvent event) {
        switchFatherContent(this, event.twoFragment);
    }

}
