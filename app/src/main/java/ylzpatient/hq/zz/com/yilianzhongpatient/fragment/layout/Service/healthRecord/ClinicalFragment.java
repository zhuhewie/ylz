package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.healthRecord;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.ReportClinicalAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestCountResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PagePatIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ClinicalBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.HealthPatientBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClinicalFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 健康档案的临床档案界面
 */
public class ClinicalFragment extends SuperFragment {
    private static final String ARG_PATIENT = "patient";

    @BindView(R.id.rv_clinical)
    RecyclerView rvClinical;
    @BindView(R.id.srl_clinical)
    SwipeRefreshLayout srlClinical;

    private HealthPatientBean patient;

    private PagePatIdRequestBean request;
    private LinearLayoutManager llManager;
    private ReportClinicalAdapter clinicalAdapter;
    private List<ClinicalBean> listClin;
    private int lastVisibleItem;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ClinicalFragment.
     */
    public static ClinicalFragment newInstance(HealthPatientBean patient) {
        ClinicalFragment fragment = new ClinicalFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PATIENT, patient);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patient = getArguments().getParcelable(ARG_PATIENT);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clinical, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData(false);

        return view;
    }

    private void addClick() {
        //下拉刷新
        srlClinical.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData(false);
            }
        });

        //上拉加载
        rvClinical.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == clinicalAdapter.getItemCount()) {
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
     * 初始化界面数据
     */
    private void initView() {
        listClin = new ArrayList<>();
        request = new PagePatIdRequestBean();
        llManager = new LinearLayoutManager(getContext());
        clinicalAdapter = new ReportClinicalAdapter(listClin);

        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvClinical.setLayoutManager(llManager);
        //rvPatient.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvClinical.setAdapter(clinicalAdapter);

        request.setPatientId(patient.getPatientId());
        request.setPageNum(0);
        request.setPageSize(GeneralConfig.PageSize);
    }

    private void loadingData(final boolean addMore) {
        srlClinical.setRefreshing(true);
        HttpMethods.getInstance().getReportClinical(new Subscriber<ResquestCountResult<List<ClinicalBean>>>() {
            @Override
            public void onCompleted() {
                srlClinical.setRefreshing(false);

            }

            @Override
            public void onError(Throwable e) {
                Log.e(GeneralConfig.LOG_TAG, "获取健康档案临床报告失败:" +e.toString());
            }

            @Override
            public void onNext(ResquestCountResult<List<ClinicalBean>> result) {
                Log.i(GeneralConfig.LOG_TAG, "获取健康档案临床报告成功:" + result);
                listClin.clear();
                listClin.addAll(result.ResultData);
                clinicalAdapter.notifyDataSetChanged();

                if (!Uitls.isEmptyClazz(result)) {
                    if (TextUtils.isEmpty(result.ResultCode)) {
                        Log.i(GeneralConfig.LOG_TAG, "获取患者健康档案列表成功:" +result.toString());
                        //获取档案列表成功
                        if (!addMore) {//如果是刷新,清空现在的列表
                            listClin.clear();
                        }
                        listClin.addAll(result.ResultData);
                        clinicalAdapter.notifyDataSetChanged();

                        //处理是否可以加载下一页
                        if (result.ResultCount>listClin.size()) {
                            request.setPageNum(request.getPageNum()+1);
                        }

                    } else {
                        //获取档案列表失败
                        Log.i(GeneralConfig.LOG_TAG, "获取患者健康档案列表失败:"+ result.ResultMsg);
                    }
                } else {
                    Log.i(GeneralConfig.LOG_TAG, "获取患者健康档案列表为空");
                }
            }
        }, request);
    }

}
