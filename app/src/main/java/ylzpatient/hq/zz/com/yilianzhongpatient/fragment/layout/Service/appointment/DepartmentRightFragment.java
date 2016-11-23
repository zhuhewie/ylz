package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.DepartmentAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.DepartmentLeftEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.DepartmentRightEvent;

/**
 * Created by Administrator on 2016-9-2.
 * 预约科室右边科室列表,是左边科室的子列表
 */
public class DepartmentRightFragment extends SuperFragment {
    private static final String ARG_CHILDDEPARTMENT = "childDapartment";


    private RecyclerView recyclerDepartment;//医院科室列表
    private DepartmentBean checkDepartment ;
    private List<DepartmentBean> listChildDepartment = new ArrayList<>();

    DepartmentAdapter dAdapter;


    public static DepartmentRightFragment newInstance(DepartmentBean department) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_CHILDDEPARTMENT, department);
        DepartmentRightFragment fragment = new DepartmentRightFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            checkDepartment = bundle.getParcelable(ARG_CHILDDEPARTMENT);
            listChildDepartment = checkDepartment.getChildDept();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sa_hospital_department_leftlist,container,false);
        initView(v);
        return v;
    }

    private void initView(View v) {

        EventBus.getDefault().register(this);

        //listChildDepartment ;
        //addStrengthData();

        recyclerDepartment = (RecyclerView) v.findViewById(R.id.recycler_department);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerDepartment.setLayoutManager(llm);

        dAdapter = new DepartmentAdapter(listChildDepartment,2);
        recyclerDepartment.setAdapter(dAdapter);

        //小预约科室条目点击事件
        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerDepartment);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                EventBus.getDefault().post(new DepartmentRightEvent(listChildDepartment.get(position)));
            }
        });

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }



    /**
     * 左侧科室列表条目点击相应
     * @param event 点击的条目科室信息
     * 根据新获得的数据,刷新右侧列表
     */
    @Subscribe
    public void onMessageEvent(DepartmentLeftEvent event){
        //addData(event.departmentName);
        listChildDepartment.clear();
        this.checkDepartment = event.checkDepartment;
        if(this.checkDepartment.getChildDept() != null) {
            listChildDepartment.addAll(this.checkDepartment.getChildDept());
            dAdapter.notifyDataSetChanged();
        }
    }




}
