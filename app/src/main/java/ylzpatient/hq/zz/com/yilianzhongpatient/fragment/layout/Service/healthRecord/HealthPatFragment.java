package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.healthRecord;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.HealthReportAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestCountResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PageHealthPatRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthPatientBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HealthPatFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 健康档案患者列表界面
 */
public class HealthPatFragment extends SuperFragment {
    //    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.rv_patient)
    RecyclerView rvPatient;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.srl_health_patient)
    SwipeRefreshLayout srlHealthPatient;

    private PageHealthPatRequestBean healRequest; //健康列表的请求体
    private HealthReportAdapter reportAdapter;
    private List<HealthPatientBean> listPatReport; //健康列表
    private LinearLayoutManager llManager;
    private int lastVisibleItem;




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HealthPatFragment.
     */
    public static HealthPatFragment newInstance() {
        HealthPatFragment fragment = new HealthPatFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData(false);
        return view;
    }

    /**
     * 联网加载数据
     * @param addMore 是否清空现在的列表 false:清空现在的列表
     *                true:不清空,在原来的列表上,在添加
     */
    private void loadingData(final boolean addMore) {
        if (UserManager.getInstance().getCurrentUser() != null) {
            srlHealthPatient.setRefreshing(true);
            healRequest.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
            HttpMethods.getInstance().getHealthPatients(new Subscriber<ResquestCountResult<List<HealthPatientBean>>>() {
                @Override
                public void onCompleted() {
                    srlHealthPatient.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    Snackbar.make(rvPatient, "获取患者健康档案列表失败", Snackbar.LENGTH_LONG).show();
                    Log.e(GeneralConfig.LOG_TAG, "获取患者健康档案列表出现异常:" + e.toString());
                }

                @Override
                public void onNext(ResquestCountResult<List<HealthPatientBean>> listHealResult) {
                    if (!Uitls.isEmptyClazz(listHealResult)) {
                        if (TextUtils.isEmpty(listHealResult.ResultCode)) {
                            Log.i(GeneralConfig.LOG_TAG, "获取患者健康档案列表成功:" +listHealResult.toString());
                            //获取档案列表成功
                            if (!addMore) {//如果是刷新,清空现在的列表
                                listPatReport.clear();
                            }
                            listPatReport.addAll(listHealResult.ResultData);
                            reportAdapter.notifyDataSetChanged();

                            //处理是否可以加载下一页
                            if (listHealResult.ResultCount>listPatReport.size()) {
                                healRequest.setPageNum(healRequest.getPageNum()+1);
                            }

                        } else {
                            //获取档案列表失败
                            Log.i(GeneralConfig.LOG_TAG, "获取患者健康档案列表失败:"+ listHealResult.ResultMsg);
                        }
                    } else {
                        Log.i(GeneralConfig.LOG_TAG, "获取患者健康档案列表为空");
                    }
                }
            }, healRequest);
        } else {
            Snackbar.make(rvPatient, "请先登录", Snackbar.LENGTH_LONG).show();

        }
    }

    /**
     * 点击事件
     */
    private void addClick() {
        //返回键
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //条目点击事件
        ItemClickSupport.addTo(rvPatient)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        switchFatherContent(HealthPatFragment.this,PatHealDetFragment.newInstance(listPatReport.get(position)));
                    }
                });
        //下拉刷新
        srlHealthPatient.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData(false);
            }
        });

        //上拉加载
        rvPatient.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == reportAdapter.getItemCount()) {
                    loadingData(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = llManager.findLastVisibleItemPosition();
            }
        });

    }

    /**
     * 初始化界面
     */
    private void initView() {
        healRequest = new PageHealthPatRequestBean();
        listPatReport = new ArrayList<>();
        reportAdapter = new HealthReportAdapter(listPatReport);
        llManager = new LinearLayoutManager(getContext());

        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPatient.setLayoutManager(llManager);
        //rvPatient.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvPatient.setAdapter(reportAdapter);

        healRequest.setPageNum(0);
        healRequest.setPageSize(GeneralConfig.PageSize);
        textViewName.setText("健康档案");

        setSrlColor(srlHealthPatient);
    }

}
