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
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.DepartmentDoctorAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.ChooseDateDialog;

/**
 * Created by Administrator on 2016-9-12.
 * 科室可以预约的医生列表
 */
public class DoctorFragment extends SuperFragment {
    private static final String ARG_MSG = "department_doctor";
    private static final String ARG_HOSPITAL = "hospital";
    //private static final String ARG_MSG = "arg_msg";

    @BindView(R.id.recycler_doctor)
    RecyclerView recyclerDoctor;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.img_back)
    ImageView imgBack;

    private List<DoctorBean> listDoctor = new ArrayList<>();
    private DepartmentBean department;
    private Hopital hopital;

    public static DoctorFragment newInstance(DepartmentBean departmentName, Hopital hopital) {
        Bundle args = new Bundle();

        DoctorFragment fragment = new DoctorFragment();
        args.putParcelable(ARG_MSG, departmentName);
        args.putParcelable(ARG_HOSPITAL, hopital);
        //args.putParcelable(ARG_MSG, msg);msg的数据类型是自定义bean时用

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        department = getArguments().getParcelable(ARG_MSG);
        hopital = getArguments().getParcelable(ARG_HOSPITAL);
        listDoctor .addAll(department.getDoctor());
        //mChat = getArguments().getParcelable(ARG_MSG);//获取到自定义类型

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sad_doctor_layout, container, false);
        ButterKnife.bind(this, v);
        //addData();
        initView();
        return v;
    }
    private void initView() {
        textViewName.setText(department.getDoctor().get(0).DeptName);
        imgFunction.setImageResource(R.mipmap.bottom_btn_jiaofei_2x);

        //体检套餐列表
        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(OrientationHelper.VERTICAL); //纵向滑动
        recyclerDoctor.setLayoutManager(llManager);


        DepartmentDoctorAdapter doctorAdapter = new DepartmentDoctorAdapter(listDoctor);
        recyclerDoctor.setAdapter(doctorAdapter);

        //预约医生列表的点击事件
        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerDoctor);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                //EventBus.getDefault().post(new DepartmentLeftEvent(listDepartmentName.get(position)));
                switchFatherContent(DoctorFragment.this, DoctorAppDateFragment.newInstance(listDoctor.get(position),hopital));
            }
        });

        //顶部返回按钮的点击事件
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onBackPressed();
                getFragmentManager().popBackStack();
            }
        });

        //toobar 右侧日期图标的点击事件
        //点击显示日期选着的dialogfragment
        RxView.clicks(imgFunction)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //显示时间选择dialog
                        showDialog(ChooseDateDialog.newInstance(department.getMzDeptName()));
                    }
                });

    }



}
