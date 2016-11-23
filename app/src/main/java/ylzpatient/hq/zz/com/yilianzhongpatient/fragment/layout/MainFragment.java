package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.MainSwitchEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.ServiceFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me.MeFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.seeDoctor.SeeDoctorFragment;

/**
 * Created by Administrator on 2016-9-2.
 * 主界面
 * 充满整个屏幕的Fragment
 */
public class MainFragment extends SuperFragment implements BottomNavigationBar.OnTabSelectedListener
,MeFragment.SwitchFragListener,ServiceFragment.AppointmentListener{
    private static final int REQ_MSG = 10;


    @BindView(R.id.fragment_center)
    FrameLayout fragmentCenter;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bnb;//底部导航栏

    private ArrayList<SuperFragment> fragments = new ArrayList<>();
    private SuperFragment toFragment;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.base_layout, container, false);
//        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.top_layout_3);
        ButterKnife.bind(this, v);

        bnb.setMode(BottomNavigationBar.MODE_FIXED);
        bnb.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bnb
                .addItem(new BottomNavigationItem(R.mipmap.bottom_btn_fuwu_putongzhuangtai_2x, "服务").setActiveColorResource(R.color.bottomNativationBar))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_btn_jiuzhen_putongzhuangtai_2x, "就诊").setActiveColorResource(R.color.bottomNativationBar))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_btn_jiaofei_putongzhuangtai_2x, "缴费").setActiveColorResource(R.color.bottomNativationBar))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_btn_wode_putongzhuangtai_2x, "我的").setActiveColorResource(R.color.bottomNativationBar))
                .setFirstSelectedPosition(0)
                .initialise();


        fragments = getFragments();
        setDefaultFragment();
        bnb.setTabSelectedListener(this);
        //EventBus.getDefault().register(this);
        return v;
    }


    /**
     * 设置默认的
     */
    public void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_center, ServiceFragment.newInstance(this));
        transaction.commit();
    }


    private ArrayList<SuperFragment> getFragments() {
        ArrayList<SuperFragment> fragments = new ArrayList<>();
        fragments.add(ServiceFragment.newInstance(this));
        fragments.add(SeeDoctorFragment.newInstance());
        fragments.add(PayFragment.newInstance());
        fragments.add(MeFragment.newInstance(this));
        return fragments;
    }


    //当Item被选中状态
    @Override
    public void onTabSelected(int position) {
        if (fragments != null && position < fragments.size()) {
            toFragment = fragments.get(position);
        }
    }

    //当Item不被选中状态
    @Override
    public void onTabUnselected(int position) {
        switchContent(fragments.get(position),toFragment);
    }

    //当Item再次被选中状态
    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onDestroyView() {
        //EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.isAdded()) {
            ft.show(fragment);
        } else {
            ft.add(R.id.fragment_center, fragment);
        }
        ft.commitAllowingStateLoss();
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

    /**
     * 用于相应点击,切换界面的方法
     * @param event
     */
    @Subscribe
    public void onSwitchEvent(MainSwitchEvent event) {
        switchFatherContent(this,event.toFragment);
    }

    @Override
    public void onSwitchFrag(Fragment fragment) {
        switchFatherContent(this, fragment);
    }

    @Override
    public void onAppFrag(Fragment fragment) {
        switchFatherContent(this,fragment);
    }
}
