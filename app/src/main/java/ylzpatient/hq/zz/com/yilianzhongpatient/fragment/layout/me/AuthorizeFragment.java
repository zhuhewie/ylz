package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;


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
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Use the {@link AuthorizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 授权管理界面
 *
 */
public class AuthorizeFragment extends SuperFragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.tl_impower)
    TabLayout tlImpower;
    @BindView(R.id.vp_impower)
    ViewPager vpImpower;

    private List<Fragment> fragmentList;
    private List<String> listTitleName;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AuthorizeFragment.
     */
    public static AuthorizeFragment newInstance() {
        AuthorizeFragment fragment = new AuthorizeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_impower, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        initView();

        addClick();
        return view;
    }

    private void initView() {
        textViewName.setText("授权管理");
        imgFunction.setImageBitmap(Uitls.textAsBitmap("发起授权",200));

        fragmentList = new ArrayList<>();
        listTitleName = new ArrayList<>();
        fragmentList.add(MyAuthorizeFragment.newInstance());
        listTitleName.add("我的授权");
        fragmentList.add(GiveMeAuthorizeFragment.newInstance());
        listTitleName.add("授权给我");
        ScollViewPagerAdapter serAppointAdapter = new ScollViewPagerAdapter(
                getActivity().getSupportFragmentManager(), fragmentList,listTitleName);
        vpImpower.setAdapter(serAppointAdapter);


        //设置tab滚动条的颜色
        tlImpower.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        tlImpower.setupWithViewPager(vpImpower); //关联tablayout和viewpager

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
        //发起授权
        RxView.clicks(imgFunction)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        switchFatherContent(AuthorizeFragment.this,ByAuthorizeFragment.newInstance());
                    }
                });
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onPowerEvent(StartTwoEvent event) {
        switchFatherContent(this, event.twoFragment);
    }
}
