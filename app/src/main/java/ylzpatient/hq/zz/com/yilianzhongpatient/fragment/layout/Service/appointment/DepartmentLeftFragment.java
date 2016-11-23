package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.DepartmentAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.DepartmentBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.DepartmentLeftEvent;

/**
 * Created by Administrator on 2016-9-2.
 * 预约科室左边科室列表
 */
public class DepartmentLeftFragment extends SuperFragment {

    private static final String ARG_LISTDEPARTMENT = "listDapartment";
    private static final String SAVE_STATE_POSITION = "save_state_position";

    private int mCurrentPosition = -1;

    private RecyclerView recyclerDepartment;//医院科室列表
//    private List<String> listDepartmentName;
    private List<DepartmentBean> listDepartment = new ArrayList<>();
    private DepartmentAdapter dAdapter;

    public static DepartmentLeftFragment newInstance(ArrayList<DepartmentBean> list) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LISTDEPARTMENT,list);
        DepartmentLeftFragment fragment = new DepartmentLeftFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            listDepartment = bundle.getParcelableArrayList(ARG_LISTDEPARTMENT);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            dAdapter.setItemChecked(mCurrentPosition);
        } else {
            mCurrentPosition = 0;
            dAdapter.setItemChecked(0);
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

        recyclerDepartment = (RecyclerView) v.findViewById(R.id.recycler_department);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerDepartment.setLayoutManager(llm);

        dAdapter = new DepartmentAdapter(listDepartment,1);
        recyclerDepartment.setAdapter(dAdapter);

        ItemClickSupport itemClickSupport = ItemClickSupport.addTo(recyclerDepartment);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                //showContent(position);
                if (position != mCurrentPosition) {
                    mCurrentPosition = position;
                    dAdapter.setItemChecked(position);
                    dAdapter.notifyDataSetChanged();

                    EventBus.getDefault().post(new DepartmentLeftEvent(listDepartment.get(position)));
                }
            }
        });
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_POSITION, mCurrentPosition);
    }

}
