package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.AppoinHospitalAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.StartTwoEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;

/**
 * Created by Administrator on 2016-9-1.
 * 按医院预约的fragment
 */
public class HospitalFragment extends SuperFragment {
    @BindView(R.id.recycler_appoin_hospital)
    RecyclerView recyclerAppoinHospital;

//    private List<HopitalBean> listHospital;
    private List<Hopital> listHospital;
    private AppoinHospitalAdapter hospitalAdapter;
    public static HospitalFragment newInstance() {
        HospitalFragment fragment = new HospitalFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ser_appoin_hospital_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();
        addData();
        return v;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(OrientationHelper.VERTICAL); //设置垂直方向滑动的列表
        recyclerAppoinHospital.setLayoutManager(llManager);

        //模拟数据
        listHospital = new ArrayList<>();

        hospitalAdapter = new AppoinHospitalAdapter(listHospital);

        recyclerAppoinHospital.setAdapter(hospitalAdapter);
    }

    /**
     * 界面的点击事件
     */
    private void addClick(){
        //可以预约医院的列表点击事件
        ItemClickSupport.addTo(recyclerAppoinHospital)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                //EventBus.getDefault().post(new StartTwoEvent(DepartmentFragment.newInstance(listHospital.get(position))));
                EventBus.getDefault().post(new StartTwoEvent(Department2Fragment.newInstance(listHospital.get(position))));
            }
        });
    }

    /**
     * 加载医院数据
     */
    private void addData() {
        Subscriber<ResquestResult<List<Hopital>>> subscriber = new Subscriber<ResquestResult<List<Hopital>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String s = e.toString();
            }

            @Override
            public void onNext(ResquestResult<List<Hopital>> result) {
                listHospital.clear();
                listHospital.addAll(result.ResultData);
                hospitalAdapter.notifyDataSetChanged();

            }
        };
        HttpMethods.getInstance().getHospital(subscriber);
    }


}
