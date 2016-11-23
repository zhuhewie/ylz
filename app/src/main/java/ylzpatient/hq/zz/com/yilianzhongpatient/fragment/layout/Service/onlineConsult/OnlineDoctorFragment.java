package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.OnlineDoctorAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestCountResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.PageRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QueueRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.OnlineDoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.MainSwitchEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.PatientCardDialogEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.StartTwoEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.AttentionDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;

/**
 * Created by Administrator on 2016-9-30.
 * 在线医生列表界面
 */
public class OnlineDoctorFragment extends SuperFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.recy_doctor)
    RecyclerView recyDoctor;
    @BindView(R.id.srl_onlineDoctor)
    SwipeRefreshLayout srlOnlineDoctor;
    @BindView(R.id.img_tip)
    ImageView imgTip;
    @BindView(R.id.ll_tip)
    LinearLayout llTip;

    private OnlineDoctorAdapter doctorAdapter;
    private List<OnlineDoctorBean> listDoctor;
    private OnlineDoctorBean chooseDoctor;//选中的医生
    private PageRequestBean page;
    private LinearLayoutManager llManager;
    private int lastVisibleItem;

    public static OnlineDoctorFragment newInstance() {
        OnlineDoctorFragment fragment = new OnlineDoctorFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
//        if (bundle != null) {
//            appMessage = bundle.getParcelable(ARG_APP_MESS);
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.online_doctor_layout, container, false);
        ButterKnife.bind(this, v);
        initView();
        addClick();
        loadingData();//添加数据
        return v;
    }

    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //关闭提示
        RxView.clicks(imgTip)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        llTip.setVisibility(View.GONE);
                    }
                });
        //医生列表子控件的点击事件
        doctorAdapter.setChildClickListener(new OnlineDoctorAdapter.OnItemChildClickListener() {
            @Override
            public void onClick(View view, int position) { //医生的点击事件
                switchFatherContent(OnlineDoctorFragment.this, DoctorDetailFragment.newInstance(listDoctor.get(position)));
                chooseDoctor = listDoctor.get(position);

            }

            @Override
            public void onWenZenClick(View view, int position) { //问诊的点击事件
                chooseDoctor = listDoctor.get(position);
//                switchFatherContent(OnlineDoctorFragment.this, AttentionDialog.newInstance());
                showDialog(AttentionDialog.newInstance());
            }
        });

        //下拉刷新数据
        srlOnlineDoctor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
            }
        });

        //上拉加载
        recyDoctor.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == doctorAdapter.getItemCount()) {
                    loadingData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = llManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initView() {
        page = new PageRequestBean();
        chooseDoctor = new OnlineDoctorBean();
        textViewName.setText("在线医生");
        page.setPageNum(0);
        page.setPageSize(15);
        llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyDoctor.setLayoutManager(llManager);

        doctorAdapter = new OnlineDoctorAdapter();
        recyDoctor.setAdapter(doctorAdapter);
        listDoctor = new ArrayList<>();

        //刷新的颜色
        srlOnlineDoctor.setColorSchemeColors(getResources().getColor(R.color.bule), getResources().getColor(R.color.yellow),
                getResources().getColor(R.color.read), getResources().getColor(R.color.green));

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        srlOnlineDoctor.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
    }

    private void loadingData() {
        srlOnlineDoctor.setRefreshing(true);
        HttpMethods.getInstance().getOnlineDoctor(new Subscriber<ResquestCountResult<List<OnlineDoctorBean>>>() {
            @Override
            public void onCompleted() {
                page.setPageNum(page.getPageNum() + 1);
                doctorAdapter.setData(listDoctor);
                doctorAdapter.notifyDataSetChanged();
                srlOnlineDoctor.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(GeneralConfig.LOG_TAG, "获取在线医生列表失败:" + e.toString());
                srlOnlineDoctor.setRefreshing(false);

            }

            @Override
            public void onNext(ResquestCountResult<List<OnlineDoctorBean>> listResult) {
                listDoctor.addAll(listResult.ResultData);
                if (listResult.ResultCount == page.getPageSize()) {
                    page.setPageNum(page.getPageNum() + 1);
                }
            }
        }, page);

    }

    //接收注意事项界面的点击事件
    @Subscribe
    public void inChooseCard(StartTwoEvent event) {
        ChoosePatientCardFragment fragment = (ChoosePatientCardFragment) event.twoFragment;
        fragment.setChooseDoctor(this.chooseDoctor);
        switchFatherContent(OnlineDoctorFragment.this, fragment);
    }

    //接收选中的就诊卡
    @Subscribe
    public void selectCard(PatientCardDialogEvent event) {
//        ChoosePatientCardFragment fragment = (ChoosePatientCardFragment) event.twoFragment;
//        fragment.setChooseDoctor(this.chooseDoctor);event.patientCard
        QueueRequestBean queueRequest = new QueueRequestBean();
        queueRequest.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
        queueRequest.setDepartment(chooseDoctor.getDepartment());
        queueRequest.setDepartmentCode(chooseDoctor.getDepartmentCode());
        queueRequest.setDoctorName(chooseDoctor.getDoctorName());
        queueRequest.setDoctorCode(chooseDoctor.getDoctorCode());
        queueRequest.setDoctorId(chooseDoctor.getDoctorId());
        queueRequest.setHospitalId(chooseDoctor.getHospitalId());
        queueRequest.setHospitalId(chooseDoctor.getHospitalId());
        queueRequest.setPatientId(event.patientCard.getPatId());
        queueRequest.setPatientName(event.patientCard.getPatName());
        getFragmentManager().popBackStack();
        //切换界面
        EventBus.getDefault().post(new MainSwitchEvent(WaitFragment.newInstance(queueRequest,null)));

        //switchFatherContent(OnlineDoctorFragment.this, WaitFragment.newInstance(queueRequest,null));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}
