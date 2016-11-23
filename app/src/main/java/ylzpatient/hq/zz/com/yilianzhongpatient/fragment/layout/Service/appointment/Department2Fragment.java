package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
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
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.DepartmentAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.JsonParam;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-9-1.
 * 预约某医院科室列表的fragment
 */
public class Department2Fragment extends SuperFragment {
    private static final String ARG_HOSPITAL = "arg_hospital";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.recycler_left_depa)
    RecyclerView recyclerLeftDepa;
    @BindView(R.id.recycler_right_depa)
    RecyclerView recyclerRightDepa;
    private Hopital hospital;

    private int mCurrentPosition = -1;

    private ArrayList<DepartmentBean> departmentList = new ArrayList<>();
    private DepartmentAdapter leftAdapter;
    private DepartmentAdapter rightAdapter;
    private DepartmentBean checkDepartment;

    public static Department2Fragment newInstance(Hopital hopital) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_HOSPITAL, hopital);

        Department2Fragment fragment = new Department2Fragment();
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
        View v = inflater.inflate(R.layout.sa_hospital_department2_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        return v;
    }


    private void initView() {
        textViewName.setText(hospital.getOrgName());

        LinearLayoutManager llmLeft = new LinearLayoutManager(getContext());
        llmLeft.setOrientation(OrientationHelper.VERTICAL);
        recyclerLeftDepa.setLayoutManager(llmLeft);

        LinearLayoutManager llmRight = new LinearLayoutManager(getContext());
        llmRight.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRightDepa.setLayoutManager(llmRight);

        leftAdapter = new DepartmentAdapter();
        recyclerLeftDepa.setAdapter(leftAdapter);
        rightAdapter = new DepartmentAdapter();
        recyclerRightDepa.setAdapter(rightAdapter);


        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerLeftDepa);
        //左侧列表点击事件
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                //showContent(position);
                if (position != mCurrentPosition) {
                    mCurrentPosition = position;
                    leftAdapter.setItemChecked(position);
                    leftAdapter.notifyDataSetChanged();

                    checkDepartment = departmentList.get(position);
                    rightAdapter.setData(checkDepartment.getChildDept(),2);
                    rightAdapter.notifyDataSetChanged();
                    //EventBus.getDefault().post(new DepartmentLeftEvent(listDepartment.get(position)));
                }
            }
        });

        //右侧小预约科室条目点击事件
        ItemClickSupport itemClick = ItemClickSupport.addTo(recyclerRightDepa);
        itemClick.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                //EventBus.getDefault().post(new DepartmentRightEvent(listChildDepartment.get(position)));
                switchFatherContent(Department2Fragment.this, DoctorFragment.newInstance(checkDepartment.getChildDept().get(position),hospital));
            }
        });
        //顶部左边的返回按钮的点击事件
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });

        //联网获取数据
        HttpMethods.getInstance().getDepartAndDoctorString(new Subscriber<List<DepartmentBean>>() {
            @Override
            public void onCompleted() {
                leftAdapter.setData(departmentList,1);
                if (departmentList != null && departmentList.size()>0) {
                    checkDepartment = departmentList.get(0);
                    rightAdapter.setData(checkDepartment.getChildDept(),2);
                }
                leftAdapter.setItemChecked(0);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
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
        }, getTime());


    }

    /**
     * 获取请求科室和医生的请求体的bean
     *
     * @return
     */
    private JsonParam<TimeBean> getTime() {
        TimeBean time = new TimeBean();
        time.setBeginTime(Uitls.getTime(0));
        time.setEndTime(Uitls.getTime(30));
        return new JsonParam<>(time);
    }

}
