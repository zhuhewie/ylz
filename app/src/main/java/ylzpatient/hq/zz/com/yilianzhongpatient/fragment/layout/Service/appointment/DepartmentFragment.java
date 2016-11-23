package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.JsonParam;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.DepartmentRightEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-1.
 * 预约某医院科室列表的fragment
 */
public class DepartmentFragment extends SuperFragment {
    private static final String ARG_HOSPITAL = "arg_msg";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    private Hopital hospital;

    private ArrayList<DepartmentBean> departmentList = new ArrayList<>();

    public static DepartmentFragment newInstance(Hopital hopital) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_HOSPITAL, hopital);

        DepartmentFragment fragment = new DepartmentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            hospital = bundle.getParcelable(ARG_HOSPITAL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sa_hospital_department_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        return v;
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

    private void initView() {
        textViewName.setText(hospital.getOrgName());
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //加载科室列表和医生的数据
//        HttpMethods.getInstance().getDepartAndDoctor(new Subscriber<D<List<DepartmentBean>>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                String s = e.toString();
//            }
//
//            @Override
//            public void onNext(D<List<DepartmentBean>> d) {
//                List<DepartmentBean> list = new ArrayList<DepartmentBean>();
//                list.addAll(d.d);
//            }
//        },getTime());

        HttpMethods.getInstance().getDepartAndDoctorString(new Subscriber<List<DepartmentBean>>() {
            @Override
            public void onCompleted() {
                addOldView();
            }

            @Override
            public void onError(Throwable e) {
                String s = e.toString();

            }

            @Override
            public void onNext(List<DepartmentBean> listDepartment) {
                departmentList.clear();
                departmentList.addAll(listDepartment);
            }
        },getTime());




    }


    //旧的加载科室列表的默认界面,左右两个Fragment组成默认界面
    private void addOldView() {
        addDeulftFragment(R.id.department_left, DepartmentLeftFragment.newInstance(departmentList));
        addDeulftFragment(R.id.department_right, DepartmentRightFragment.newInstance(departmentList.get(0)));
    }

    @Subscribe
    public void onEvent(DepartmentRightEvent event) {
        switchFatherContent(this, DoctorFragment.newInstance(event.departmentName,hospital));
    }

    /**
     * 获取请求科室和医生的请求体的bean
     * @return
     */
    private JsonParam<TimeBean> getTime() {
        TimeBean time = new TimeBean();
        time.setBeginTime(Uitls.getTime(0));
        time.setEndTime(Uitls.getTime(10));
        return new JsonParam<>(time);
    }

}
