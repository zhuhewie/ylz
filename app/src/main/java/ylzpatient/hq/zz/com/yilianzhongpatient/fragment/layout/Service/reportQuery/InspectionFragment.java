package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.reportQuery;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.MainInspectAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.MainInspeRequest;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.MainInspectionBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.StartTwoEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;

import static ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls.getTime;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link InspectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InspectionFragment extends SuperFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.rv_inspection)
    RecyclerView rvInspection;
    @BindView(R.id.srl_inspection)
    SwipeRefreshLayout srlInspection;

    private MainInspeRequest inspeRequest;//联网获取主检验报告列表的请求体
    private MainInspectAdapter inspectAdapter;//列表的的适配器
    private List<MainInspectionBean> listInspe; //要显示的检验报告数据
    private LinearLayoutManager llManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InspectionFragment.
     */
    public static InspectionFragment newInstance() {
        InspectionFragment fragment = new InspectionFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inspection, container, false);
        ButterKnife.bind(this, view);
        initView();
        loadingData();
        addClick();
        return view;
    }

    //初始化界面
    private void initView() {
        inspeRequest = new MainInspeRequest();
        inspectAdapter = new MainInspectAdapter();
        listInspe = new ArrayList<>();
        llManager = new LinearLayoutManager(getContext());

        inspeRequest.setPatientID("194609");
        inspeRequest.setBeginDate(getTime(0));
        inspeRequest.setEndDate(getTime(30));

        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvInspection.setLayoutManager(llManager);
        rvInspection.setAdapter(inspectAdapter);

        setSrlColor(srlInspection);

    }

    //界面点击事件
    private void addClick() {
        //下拉刷新的监听
        srlInspection.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
            }
        });
        //条目的点击事件
        ItemClickSupport.addTo(rvInspection)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
//                        Snackbar.make(rvInspection,"点我也不给你反应",Snackbar.LENGTH_LONG).show();
                        //switchFatherContent(InspectionFragment.this,InspecDetialFragment.newInstance(listInspe.get(position)));
                        EventBus.getDefault().post(new StartTwoEvent(InspecDetialFragment.newInstance(listInspe.get(position))));

                    }
                });
    }

    //联网加载检验报告信息
    private void loadingData() {
        srlInspection.setRefreshing(true);
        HttpMethods.getInstance().getListInspec(new Subscriber<List<MainInspectionBean>>() {
            @Override
            public void onCompleted() {
                srlInspection.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                srlInspection.setRefreshing(false);
                Log.e(GeneralConfig.LOG_TAG,"检查主列表获取数据失败:"+e.toString());
            }

            @Override
            public void onNext(List<MainInspectionBean> listInspection) {
                if (listInspection!=null && listInspection.size()>0) {
                    Log.i(GeneralConfig.LOG_TAG,"检查主列表获取数据成功:"+listInspection.toString());
                    listInspe.clear();
                    listInspe.addAll(listInspection);
                    inspectAdapter.setData(listInspe);
                } else {
                    Snackbar.make(srlInspection,"没有数据",Snackbar.LENGTH_LONG).show();
                    Log.i(GeneralConfig.LOG_TAG,"检查主列表获取数据成功,但是数据为空");
                }
            }
        }, inspeRequest);
    }
}
