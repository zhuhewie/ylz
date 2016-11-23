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
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.CheckAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.CheckListRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.CheckBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.StartTwoEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.DividerItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @BindView(R.id.rv_check)
    RecyclerView rvCheck;
    @BindView(R.id.srl_check)
    SwipeRefreshLayout srlCheck;

    private CheckListRequestBean request; //获取检验报告的请求体
    private CheckAdapter adapter;
    private LinearLayoutManager llManager;
    private List<CheckBean> listCheckReport;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CheckFragment.
     */
    public static CheckFragment newInstance() {
        CheckFragment fragment = new CheckFragment();
        //Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData();
        return view;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        adapter = new CheckAdapter();
        request = new CheckListRequestBean();
        llManager = new LinearLayoutManager(getContext());
        listCheckReport = new ArrayList<>();

        request.setAdmissTimes(1);
        request.setPatientID("000014166300");
        request.setBeginDate("2011-12-1");
        request.setEndDate("2016-12-30");

        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCheck.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
        rvCheck.setLayoutManager(llManager);
        rvCheck.setAdapter(adapter);
    }

    /**
     * 界面点击事件
     */
    private void addClick() {
        //列表点击事件
        ItemClickSupport.addTo(rvCheck)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        EventBus.getDefault().post(new StartTwoEvent(
                                CheckDetialFragment.newInstance(listCheckReport.get(position)
                                        .getCheckItemList().get(0))));

                    }
                });

        //下拉刷新监听
        srlCheck.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
            }
        });
    }

    /**
     * 联网加载检查列表的数据
     */
    private void loadingData() {
        srlCheck.setRefreshing(true);
        HttpMethods.getInstance().getCheckList(new Subscriber<List<CheckBean>>() {
            @Override
            public void onCompleted() {
                srlCheck.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                srlCheck.setRefreshing(false);
                Snackbar.make(rvCheck, "获取数据出现异常", Snackbar.LENGTH_LONG).show();
                Log.i(GeneralConfig.LOG_TAG, "获取数据出现异常" + e.toString());
            }

            @Override
            public void onNext(List<CheckBean> listCheck) {
                if (listCheck != null && listCheck.size() > 0) {
                    Log.i(GeneralConfig.LOG_TAG, "检查列表获取数据成功:" + listCheck.toString());
                    listCheckReport.clear();
                    listCheckReport.addAll(listCheck);
                    adapter.setData(listCheckReport);
                } else {
                    Snackbar.make(rvCheck, "没有数据", Snackbar.LENGTH_LONG).show();
                    Log.i(GeneralConfig.LOG_TAG, "检查列表获取到的数据为空");
                }
            }
        }, request);
    }

}
