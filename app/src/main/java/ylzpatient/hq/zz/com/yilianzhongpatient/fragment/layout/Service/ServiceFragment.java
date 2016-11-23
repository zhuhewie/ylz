package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.JoinHospitalAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.NewAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.ServiceFunctionAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.PhySerAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter.SerRollAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserIdRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.IndexBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.QueueBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.MainSwitchEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment.AppoimtFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.appointment.Department2Fragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.healthRecord.HealthPatFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.medical.MedicalFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult.ClinicFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult.OnlineDoctorFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult.WaitFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.reportQuery.ReportQueryFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me.LoginFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.DividerItemDecoration;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.ListViewDecoration;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-8-24.
 */
public class ServiceFragment extends SuperFragment {
    @BindView(R.id.ab_view_name)
    TextView abViewName;
    @BindView(R.id.sao_ma)
    ImageView saoMa;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.ab_right_img)
    LinearLayout abRightImg;
    @BindView(R.id.vp_ad_img)
    ViewPager vpAdImg;
    @BindView(R.id.rl_image)
    RelativeLayout rlImage;
    @BindView(R.id.function_recycler_view)
    RecyclerView functionRecyclerView;
    @BindView(R.id.rv_join_hospital)
    RecyclerView rvJoinHospital;
    @BindView(R.id.layout_phy_ex)
    RelativeLayout layoutPhyEx;
    @BindView(R.id.phy_exa_recycle)
    RecyclerView phyExaRecycle;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.rl_join_hos)
    RelativeLayout rlJoinHos;
    @BindView(R.id.srl_index)
    SwipeRefreshLayout srlIndex;

    private String[] text = {"门诊预约", "排号", "报告查询", "健康档案",
            "费用查询", "体检商城", "就诊指南", "在线诊室"};
    private int[] img = {R.mipmap.bottom_btn_menzhenyuyue2x, R.mipmap.bottom_btn_paihao2x,
            R.mipmap.bottom_btn_baogaochaxun2x, R.mipmap.bottom_btn_jiankangdangan2x,
            R.mipmap.bottom_btn_feiyongchaxun2x, R.mipmap.bottom_btn_tijianshangcheng2x,
            R.mipmap.bottom_btn_jiuzhenzhinan2x, R.mipmap.bottom_btn_zaixianzhenshi2x
    };

    private boolean isStop = false;  //是否停止子线程  不会停止
    private ServiceFunctionAdapter serFunAdapter;//功能列表的adapter
    private PhySerAdapter phySerAdapter;//体检套餐列表的adapter
    private Subscription subscribe_auto;
    private SerRollAdapter serRollAdapter;
    private JoinHospitalAdapter joinHosAdapter;
    private NewAdapter newsAdapter;
    private List<IndexBean.AdsBean> listAds;

    private List<IndexBean.HospitalsBean> listHos;
    private UserIdRequestBean request;//在线信息排队信息的请求体

    public interface AppointmentListener {
        void onAppFrag(Fragment fragment);
    }

    public static ServiceFragment newInstance(Fragment f) {
        Bundle args = new Bundle();

        ServiceFragment fragment = new ServiceFragment();
        fragment.setArguments(args);
        fragment.setTargetFragment(f, 300);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.service_layout, container, false);

        ButterKnife.bind(this, v);

        ViewGroup.LayoutParams para = vpAdImg.getLayoutParams();//获取按钮的布局
        para.width = GeneralConfig.screenWidth;//修改宽度
        para.height = (int) (GeneralConfig.screenWidth / (2.8));//修改高度
        vpAdImg.setLayoutParams(para); //设置修改后的布局。

        initData();//初始化界面

        addClick();//界面点击事件

        loadingData(); //联网加载数据
        return v;
    }

    /**
     * 获取首页数据
     */
    private void loadingData() {
        srlIndex.setRefreshing(true);
        HttpMethods.getInstance().getIndex(new Subscriber<IndexBean>() {
            @Override
            public void onCompleted() {
                srlIndex.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(functionRecyclerView, "获取首页信息数据失败", 3000).show();
                Log.e(GeneralConfig.LOG_TAG, "获取首页信息时出现异常:" + e.toString());
                srlIndex.setRefreshing(false);
            }

            @Override
            public void onNext(IndexBean indexBean) {
                if (!Uitls.isEmptyClazz(indexBean)) {
                    Log.i(GeneralConfig.LOG_TAG, "获取到的首页信息" + indexBean.toString());
                    listHos.clear();
                    listHos.addAll(indexBean.getHospitals());
                    joinHosAdapter.setData(listHos);
                    phySerAdapter.setData(indexBean.getMedicalPackages());
                    newsAdapter.setData(indexBean.getNews());

                    listAds.clear();
                    listAds.addAll(indexBean.getAds());
                    serRollAdapter.setData(listAds);
                    if (listAds.size() > 1) { //判断是否开启轮播
                        autoLoop(); //轮询器
                    } else {
                        stopLoop();
                    }
                    serRollAdapter.notifyDataSetChanged();
                } else {
                    Snackbar.make(functionRecyclerView, "获取首页信息为空", 3000).show();
                    Log.i(GeneralConfig.LOG_TAG, "获取到的首页信息" + indexBean.toString());
                }
            }
        });
    }


    private void initData() {
        listHos = new ArrayList<>();
        listAds = new ArrayList<>();
        request = new UserIdRequestBean();

        //刷新的颜色
        srlIndex.setColorSchemeColors(getResources().getColor(R.color.bule), getResources().getColor(R.color.yellow),
                getResources().getColor(R.color.read), getResources().getColor(R.color.green));
        //设置大小
        //srlIndex.setSize(SwipeRefreshLayout.LARGE);

        //功能列表
        GridLayoutManager glManager = new GridLayoutManager(getContext(), 4);
        glManager.setOrientation(OrientationHelper.VERTICAL); //设置垂直方向滑动的列表
        functionRecyclerView.setLayoutManager(glManager);
        functionRecyclerView.setItemAnimator(new DefaultItemAnimator()); //设置增加移除动画

        //设置功能列表的adapter
        serFunAdapter = new ServiceFunctionAdapter(getContext(), text, img);
        functionRecyclerView.setAdapter(serFunAdapter);

        //入驻医院
        LinearLayoutManager hosLlManager = new LinearLayoutManager(getContext());
        hosLlManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvJoinHospital.setLayoutManager(hosLlManager);

        rvJoinHospital.addItemDecoration(new ListViewDecoration());//添加分割线
        joinHosAdapter = new JoinHospitalAdapter();
        rvJoinHospital.setAdapter(joinHosAdapter);

        //体检套餐列表
        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(OrientationHelper.VERTICAL); //纵向滑动
        phyExaRecycle.setLayoutManager(llManager);

        //体检套餐设置adapter
        phySerAdapter = new PhySerAdapter();
        phyExaRecycle.setAdapter(phySerAdapter);

        //咨询
        LinearLayoutManager newsllManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(OrientationHelper.VERTICAL); //纵向滑动
        rvNews.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        rvNews.setLayoutManager(newsllManager);

        newsAdapter = new NewAdapter();
        rvNews.setAdapter(newsAdapter);

//        int[] imageIDs = {R.mipmap.picture_2x};
        serRollAdapter = new SerRollAdapter(listAds);
        vpAdImg.setAdapter(serRollAdapter);

    }

    /**
     * 界面控件的点击事件
     */
    private void addClick() {
        //下拉刷新
        srlIndex.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
            }
        });

        //功能列表的点击事件
        ItemClickSupport.addTo(functionRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        switch (position) {
                            //点击门诊预约
                            case 0:
                                // 通知MainActivity跳转至AppoimtFragment
                                //EventBus.getDefault().post(new StartBrotherEvent(AppoimtFragment.newInstance()));
                                sendBackResult();
                                break;
                            case 2:
                                if (UserManager.getInstance().getCurrentUser() != null) {
                                    //跳转到报告查询界面
                                    EventBus.getDefault().post(new MainSwitchEvent(ReportQueryFragment.newInstance()));
                                } else {
                                    //跳转到登录界面,登录
                                    EventBus.getDefault().post(new MainSwitchEvent(LoginFragment.newInstance()));
                                }
                                break;
                            case 3:
                                if (UserManager.getInstance().getCurrentUser() != null) {
                                    //跳转到健康档案界面
                                    EventBus.getDefault().post(new MainSwitchEvent(HealthPatFragment.newInstance()));
                                } else {
                                    //跳转到登录界面,登录
                                    EventBus.getDefault().post(new MainSwitchEvent(LoginFragment.newInstance()));
                                }
                                break;
                            case 5:
                                if (UserManager.getInstance().getCurrentUser() != null) {
                                    //跳转到健康档案界面
                                    EventBus.getDefault().post(new MainSwitchEvent(MedicalFragment.newInstance()));
                                } else {
                                    //跳转到登录界面,登录
                                    EventBus.getDefault().post(new MainSwitchEvent(LoginFragment.newInstance()));
                                }
                                break;
                            case 7:
                                //跳转到在线诊室界面
                                //EventBus.getDefault().post(new MainSwitchEvent(OnlineDoctorFragment.newInstance()));
                                queue();
                                break;

                            default:
                                Snackbar.make(functionRecyclerView, "功能开发中...", Snackbar.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
        //入驻医院列表的点击事件
        ItemClickSupport.addTo(rvJoinHospital)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        IndexBean.HospitalsBean oldHospitals = listHos.get(position);
                        Hopital newHopital = new Hopital();
                        newHopital.setOrgName(oldHospitals.getName());
                        newHopital.setOrgId(oldHospitals.getHospitalId());
                        newHopital.setOrgLogo(oldHospitals.getLogo());
                        newHopital.setServerUrl(oldHospitals.getServerUrl());
                        EventBus.getDefault().post(new MainSwitchEvent(Department2Fragment.newInstance(newHopital)));

                    }
                });
        //入驻医院点击事件
        RxView.clicks(rlJoinHos)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        sendBackResult();
                    }
                });
        //体检套餐
        RxView.clicks(layoutPhyEx)
                .throttleFirst(700, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //EventBus.getDefault().post(new );
                        Snackbar.make(layoutPhyEx, "功能开发中", 3000).show();
                    }
                });
        //咨询的滑动监听
        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    //开启轮训器
    private void autoLoop() {
        if (subscribe_auto == null || subscribe_auto.isUnsubscribed()) {
            subscribe_auto = Observable.interval(3000, 3000, TimeUnit.MILLISECONDS)
                    //延时3000 ，每间隔3000，时间单位
                    //.compose(this.<Long>bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            int currentIndex = vpAdImg.getCurrentItem();
                            if (++currentIndex == serRollAdapter.getCount()) {
                                vpAdImg.setCurrentItem(0);
                            } else {
                                vpAdImg.setCurrentItem(currentIndex, true);
                            }
                        }
                    });
        }
    }

    /**
     * 停止轮询器
     */
    private void stopLoop() {
        if (subscribe_auto != null && !subscribe_auto.isUnsubscribed()) {
            subscribe_auto.unsubscribe();
        }
    }


    public void sendBackResult() {
        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed
        AppointmentListener listener = (AppointmentListener) getTargetFragment();
        listener.onAppFrag(AppoimtFragment.newInstance());
    }

    @Override
    public void onDestroyView() {
        // 销毁线程
        isStop = true;
        super.onDestroyView();
    }

    /**
     * 联网获取排队信息
     */
    private void queue() {
        if (UserManager.getInstance().getCurrentUser() != null) {
            request.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
            srlIndex.setRefreshing(true);
            HttpMethods.getInstance().currentQueue(new Subscriber<ResquestResult<QueueBean>>() {
                @Override
                public void onCompleted() {
                    srlIndex.setRefreshing(false);
                }

                @Override
                public void onError(Throwable e) {
                    Snackbar.make(functionRecyclerView, "获取首页信息数据失败", 3000).show();
                    Log.e(GeneralConfig.LOG_TAG, "获取首页信息时出现异常:" + e.toString());
                }

                @Override
                public void onNext(ResquestResult<QueueBean> queueResult) {
                    if (!Uitls.isEmptyClazz(queueResult) && TextUtils.isEmpty(queueResult.ResultCode)) {
                        QueueBean queue = queueResult.ResultData;
                        if (queue == null) {//跳转到医生列表界面
                            EventBus.getDefault().post(new MainSwitchEvent(OnlineDoctorFragment.newInstance()));
                        } else if (queue.getStatus() == 0) { //如果状态等于0,到排队界面
//                            OnlineDoctorBean doctor = new OnlineDoctorBean();
//                            PatientCardBean patientCard = new PatientCardBean();
//                            doctor.setDoctorName(queue.getDoctorName());
//                            doctor.setDepartment(queue.getDepartmentName());
//                            doctor.setHospitalName(queue.getHospitalName());
//                            patientCard.setPatName(queue.getPatientName());
                            EventBus.getDefault().post(new MainSwitchEvent(WaitFragment.newInstance(null, queue)));
                        } else if (queue.getStatus() == 1) { //如果状态等于1,到聊天界面.
                            EventBus.getDefault().post(new MainSwitchEvent(ClinicFragment.newInstance(queueResult.ResultData)));
                        }
                    } else {//加载排队信息失败
                        Snackbar.make(functionRecyclerView, "加载排队信息失败", Snackbar.LENGTH_LONG).show();
                        Log.i(GeneralConfig.LOG_TAG, "获取到的排队信息为空");
                    }
                }
            }, request);
        } else {
            //跳转到登录界面,登录
            EventBus.getDefault().post(new MainSwitchEvent(LoginFragment.newInstance()));
        }
    }
}
