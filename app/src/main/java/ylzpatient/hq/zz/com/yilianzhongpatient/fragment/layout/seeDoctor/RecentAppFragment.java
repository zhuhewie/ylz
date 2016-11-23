package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.seeDoctor;


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
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.RecentAppAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestCountResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.RecentAppBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.AppMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecentAppFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 最近预约界面
 */
public class RecentAppFragment extends SuperFragment {


    @BindView(R.id.rv_recent_app)
    RecyclerView rvRecentApp;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.tv_waring)
    TextView tvWaring;
    @BindView(R.id.srl_app_recent)
    SwipeRefreshLayout srlAppRecent;

    private RecentAppAdapter recentAppAdapter;
    private LinearLayoutManager llManager;

    private RecentAppBean recentApp;
    private UserTestBean_2 user;
    private List<AppMessBean> listApp;
    private int loadingType = 1; //刷新或者加载更多的标签,1:加载更过
    private int refrashType = 0; //刷新或者加载更多的标签,0:刷新
    //数据是否加载了全部,false:没有加载完全,可以加载上拉加载.ture :已经加载我全部的数据,关闭上拉加载
    private boolean loadingOver = false;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecentAppFragment.
     */
    public static RecentAppFragment newInstance() {
        RecentAppFragment fragment = new RecentAppFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_app, container, false);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        loadingData(refrashType);
        return view;
    }

    private void initView() {
        recentApp = new RecentAppBean();
        recentApp.setPageNum(0);
        recentApp.setPageSize(GeneralConfig.PageSize);
        user = UserManager.getInstance().getCurrentUser();
        listApp = new ArrayList<>();

        textViewName.setText("最近预约");
        if (!Uitls.isEmptyClazz(user) && !TextUtils.isEmpty(user.getUserId())) {
            recentApp.setUserId(user.getUserId());
        }
        llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecentApp.setLayoutManager(llManager);

        recentAppAdapter = new RecentAppAdapter();

        rvRecentApp.setAdapter(recentAppAdapter);
        setSrlColor(srlAppRecent);
    }

    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });

        //刷新数据
        srlAppRecent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                loadingData(refrashType);
            }
        });
        //列表滑动监听
        rvRecentApp.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = llManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition + 1 == recentAppAdapter.getItemCount() && !loadingOver) {
                    loadingData(loadingType);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        //条目点击事件
        ItemClickSupport.addTo(rvRecentApp)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        switchFatherContent(AppDetailFragment.newInstance(listApp.get(position)));
                    }
                });
    }

    /**
     * 从网络端获取数据
     */
    private void loadingData(final int refashOrloading) {
        if (!Uitls.isEmptyClazz(user)) {
            srlAppRecent.setRefreshing(true);
            HttpMethods.getInstance().getRecentApp(new Subscriber<ResquestCountResult<List<AppMessBean>>>() {
                @Override
                public void onCompleted() {
                    srlAppRecent.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    srlAppRecent.setRefreshing(false);
                    Snackbar.make(rvRecentApp, "获取最近预约信息出现异常.", 3000).show();
                    Log.e(GeneralConfig.LOG_TAG, "获取最近预约信息出现异常:" + e.getMessage()+e.getCause().getLocalizedMessage());

                }

                @Override
                public void onNext(ResquestCountResult<List<AppMessBean>> listCountResult) {
                    if (TextUtils.isEmpty(listCountResult.ResultCode)) {
                            //返回数据不为空
                        if (listCountResult.ResultData != null && listCountResult.ResultData.size() > 0) {
                            if (refashOrloading == refrashType) {//如果是刷新数据
                                listApp.clear(); //清空原来数据
                            }
                            listApp.addAll(listCountResult.ResultData);
                            recentAppAdapter.setData(listApp);
                            tvWaring.setVisibility(View.GONE);
                            if (listApp.size() >= listCountResult.ResultCount){
                                loadingOver = true;
                            } else {
                                recentApp.setPageNum(recentApp.getPageNum()+1);//页码加一,以便下一次请求下一页
                            }
                        } else {
                            Snackbar.make(rvRecentApp, "最近预约记录为空", 3000).show();
                        }
                        Log.i(GeneralConfig.LOG_TAG, "获取最近预约信息成功:" + listCountResult.ResultData.toString());
                    } else {
                        Snackbar.make(rvRecentApp, "没有最近预约信息:" + listCountResult.ResultMsg, 3000).show();
                        Log.i(GeneralConfig.LOG_TAG, "没有最近预约信息" + listCountResult.ResultMsg);
                        loadingOver = true;
                    }
                }
            }, recentApp);
        } else {
            Snackbar.make(rvRecentApp, "您还你没有登录,请先登录在查看", 3000).show();
        }

    }

}
