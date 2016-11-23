package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.ServiceAppointAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.StartTwoEvent;

/**
 * Created by Administrator on 2016-9-1.
 * 预约界面
 * 包含可以左右滑动的按医院预约界面,和 按疾病预约界面
 */
public class AppoimtFragment extends SuperFragment {


    @BindView(R.id.tablelayout_appointment)
    TabLayout tablelayoutAppointment;
    @BindView(R.id.viewPager_appointment)
    ViewPager viewPagerAppointment;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    private List<Fragment> fragmentList;

    public static AppoimtFragment newInstance() {
        AppoimtFragment fragment = new AppoimtFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.service_appointment_layout, container, false);

        ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);

        initView();
        return v;
    }

    private void initView() {
        textViewName.setText("医院列表");

        //顶部返回图标的点击事件
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });

        fragmentList = new ArrayList<>();
        fragmentList.add(HospitalFragment.newInstance());
        fragmentList.add(DiseaseFragment.newInstance());
        ServiceAppointAdapter serAppointAdapter = new ServiceAppointAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        viewPagerAppointment.setAdapter(serAppointAdapter);

        //设置tab滚动条的颜色
        tablelayoutAppointment.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        tablelayoutAppointment.setupWithViewPager(viewPagerAppointment); //关联tablayout和viewpager
    }


    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onMessageEvent(StartTwoEvent event) {
        switchFatherContent(this, event.twoFragment);
    }
}
