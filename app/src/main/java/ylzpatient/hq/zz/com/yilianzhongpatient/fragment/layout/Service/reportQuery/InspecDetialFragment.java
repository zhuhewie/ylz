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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.InspecDetialAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.InspeDetialRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.InspectionDetailBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.MainInspectionBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.DividerItemDecoration;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InspecDetialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InspecDetialFragment extends SuperFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INSPECTION = "main_inspection";
    private static final String imgBaseUrl = "http://120.196.145.38:7100/functionpage/GetLisReport.aspx?repid=";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.rv_inspection_detial)
    RecyclerView rvInspectionDetial;
    @BindView(R.id.srl_inspection_detil)
    SwipeRefreshLayout srlInspectionDetil;
    @BindView(R.id.btn_inspe_img)
    Button btnInspeImg;

    private MainInspectionBean inspection;//选中的主检验报告条目
    private InspeDetialRequestBean insDetRequest;//获取检验详情的请求体
    private InspecDetialAdapter adapter;
    private LinearLayoutManager llManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InspecDetialFragment.
     */
    public static InspecDetialFragment newInstance(MainInspectionBean inspection) {
        InspecDetialFragment fragment = new InspecDetialFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INSPECTION, inspection);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            inspection = getArguments().getParcelable(ARG_INSPECTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspec_detial, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData();
        return view;
    }

    /**
     * 初始化
     */
    private void initView() {
        insDetRequest = new InspeDetialRequestBean();
        llManager = new LinearLayoutManager(getContext());
        adapter = new InspecDetialAdapter();

        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvInspectionDetial.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));
        rvInspectionDetial.setLayoutManager(llManager);
        rvInspectionDetial.setAdapter(adapter);
        textViewName.setText(inspection.getReportName());

        insDetRequest.setRepID(inspection.getRepID());
        insDetRequest.setReportTypeValue(inspection.getReportTypeValue());
    }

    //点击事件
    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //下拉刷新监听
        srlInspectionDetil.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
            }
        });
        //报告详情图片展示界面
        RxView.clicks(btnInspeImg)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        switchFatherContent(InspecDetialFragment.this,
                                ReportImgFragment.newInstance(imgBaseUrl+inspection.getRepID()));
                    }
                });
    }

    /**
     * 联网加载数据
     */
    private void loadingData() {
        srlInspectionDetil.setRefreshing(true);
        HttpMethods.getInstance().getInspecDetialList(new Subscriber<InspectionDetailBean>() {
            @Override
            public void onCompleted() {
                srlInspectionDetil.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                srlInspectionDetil.setRefreshing(false);
                Snackbar.make(srlInspectionDetil, "获取数据出现异常", Snackbar.LENGTH_LONG).show();
                Log.i(GeneralConfig.LOG_TAG, "获取数据出现异常" + e.toString());
            }

            @Override
            public void onNext(InspectionDetailBean listInsDetai) {
                if (!Uitls.isEmptyClazz(listInsDetai)) {
                    Log.i(GeneralConfig.LOG_TAG, "检验详情列表获取数据成功:" + listInsDetai.toString());
                    adapter.setData(listInsDetai);
                } else {
                    Snackbar.make(srlInspectionDetil, "没有数据", Snackbar.LENGTH_LONG).show();
                    Log.i(GeneralConfig.LOG_TAG, "检验详情列表获取到的数据为空");
                }
            }
        }, insDetRequest);
    }

}
