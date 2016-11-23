package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.PatientCardAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.UserId;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.OnlineDoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.PatientCardDialogEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.CreatePatientCardDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-10-9.
 */
public class ChoosePatientCardFragment extends SuperFragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.rv_card)
    RecyclerView rvCard;
    @BindView(R.id.img_function)
    ImageView imgFunction;

    private List<PatientCardBean> listPatientCard;
    //private Map<PatientCardBean,Boolean> mapPatientCard;
    private PatientCardAdapter patientCardAdapter;
    private PatientCardBean patientCard;
    private OnlineDoctorBean chooseDoctor;//选中的医生

    public static ChoosePatientCardFragment newInstance() {
        ChoosePatientCardFragment fragment = new ChoosePatientCardFragment();
        return fragment;
    }

    public void setChooseDoctor(OnlineDoctorBean chooseDoctor) {
        this.chooseDoctor = chooseDoctor;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.od_choose_card_layout, container, false);
        ButterKnife.bind(this, v);

        ininView();

        addClick();

        loadingData();
        return v;
    }

    private void ininView() {
        textViewName.setText("选择就诊卡");
        imgFunction.setImageResource(R.mipmap.btn_newly_increased_2x);

        listPatientCard = new ArrayList<>();
        patientCard = new PatientCardBean();
        //mapPatientCard = new LinkedHashTreeMap<>();
        //体检套餐列表
        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(OrientationHelper.VERTICAL); //纵向滑动
        rvCard.setLayoutManager(llManager);

        //设置adapter
        patientCardAdapter = new PatientCardAdapter();
        rvCard.setAdapter(patientCardAdapter);

    }

    /**
     * 从网络端加载数据
     */
    private void loadingData() {
        UserId id = new UserId();
        if (!Uitls.isEmptyClazz(UserManager.getInstance().getCurrentUser())) {
            id.userid = UserManager.getInstance().getCurrentUser().getUserId();
            //联网加载就诊卡数据
            HttpMethods.getInstance().getPatientCard(new Subscriber<List<PatientCardBean>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    try {
                        Snackbar.make(btnCommit, "获取数据失败", 2000).show();
                        Log.i(GeneralConfig.LOG_TAG, e.toString() + "");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

                @Override
                public void onNext(List<PatientCardBean> listCard) {
                    if (listCard != null && listCard.size() > 0) {
                        listPatientCard.clear();
                        listPatientCard.addAll(listCard);
                        patientCardAdapter.setData(listPatientCard);
                        patientCardAdapter.notifyDataSetChanged();
                        patientCard = listPatientCard.get(0);
                        Log.i(GeneralConfig.LOG_TAG, "获取就诊卡:" + listPatientCard.toString());
                    }
                }
            }, id);
        } else {
//            Snackbar.make(btnCommit, "请先登录", 2000).show();
            Toast.makeText(UtilApplication.getContextObject(), "请先登录", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 界面的点击事件
     */
    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .throttleFirst(700, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //右上角的绑定就诊卡图标点击事件
        RxView.clicks(imgFunction)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //跳转到添加新的就诊卡界面
                        showDialog(CreatePatientCardDialog.newInstance());
                    }
                });
        //提交
        RxView.clicks(btnCommit)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //先返回,在传值,不然会出现界面重叠的问题
                        getFragmentManager().popBackStack();
                        //提交选中的就诊卡
                        EventBus.getDefault().post(new PatientCardDialogEvent(patientCard));
                    }
                });
        //就诊卡列表条目的点击事件
        ItemClickSupport.addTo(rvCard)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        patientCardAdapter.setSelectIndex(position);
                        patientCard = listPatientCard.get(position);
                    }
                });
    }
}
