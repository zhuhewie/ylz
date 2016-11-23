package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.seeDoctor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.SeeDoctorAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.MainSwitchEvent;

/**
 * Created by Administrator on 2016-8-24.
 */
public class SeeDoctorFragment extends SuperFragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.recy_see_doctor)
    RecyclerView recySeeDoctor;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    private ActionBar actionBar;
    private TextView topText;
    private LinearLayout topRightImageLay;
    private SeeDoctorAdapter seeAdapter;

    public static SeeDoctorFragment newInstance() {
        Bundle args = new Bundle();
        SeeDoctorFragment fragment = new SeeDoctorFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.seedoctor_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();
        return v;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        imgBack.setImageResource(R.mipmap.bottom_btn_zuijinyuyue);
        imgFunction.setImageResource(R.mipmap.bottom_btn_shaixuan_icon);
        textViewName.setText("就诊");

        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recySeeDoctor.setLayoutManager(llManager);

        seeAdapter = new SeeDoctorAdapter();
        recySeeDoctor.setAdapter(seeAdapter);
    }

    private void addClick() {
        //跳转到
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        EventBus.getDefault().post(new MainSwitchEvent(RecentAppFragment.newInstance()));
                    }
                });
    }

}
