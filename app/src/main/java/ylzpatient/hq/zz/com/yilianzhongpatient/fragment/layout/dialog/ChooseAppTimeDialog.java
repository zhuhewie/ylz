package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.ChooseAppTimeAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperDialogFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.AppTimeEvent;

/**
 * Created by Administrator on 2016-9-27.
 */
public class ChooseAppTimeDialog extends SuperDialogFragment {
    @BindView(R.id.recycler_app_time)
    RecyclerView recyclerAppTime;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;

    private static final String ARG_AMPM = "ap-pm";

    private List<TimeBean> listTime;
    private ChooseAppTimeAdapter appTimeAdapter;
    private TimeBean selectTime;
    private int ampm = 0;

    public static ChooseAppTimeDialog newInstance(int ampm) {
        ChooseAppTimeDialog frag = new ChooseAppTimeDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_AMPM, ampm);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            ampm = bundle.getInt(ARG_AMPM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_choose_app_time, container);
        ButterKnife.bind(this, view);
        initView();
        addClick();
        addData();

        return view;
    }

    private void initView() {
        textViewName.setText("预约时间段");
        listTime = new ArrayList<>();

        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(OrientationHelper.VERTICAL); //纵向滑动
        recyclerAppTime.setLayoutManager(llManager);

        appTimeAdapter = new ChooseAppTimeAdapter();
        recyclerAppTime.setAdapter(appTimeAdapter);
    }

    /**
     * 加载数据
     */
    private void addData() {
        if (ampm == 1) {
            listTime.add(new TimeBean("8:00","9:00"));
            listTime.add(new TimeBean("9:00","10:00"));
            listTime.add(new TimeBean("10:00","11:00"));
            listTime.add(new TimeBean("11:00","12:00"));
        } else if (ampm ==2) {
            listTime.add(new TimeBean("14:00",":15:00"));
            listTime.add(new TimeBean("15:00","16:00"));
            listTime.add(new TimeBean("16:00","17:00"));
            listTime.add(new TimeBean("17:00","18:00"));
        }
        appTimeAdapter.setData(listTime);
        appTimeAdapter.notifyDataSetChanged();
    }

    /**
     * 添加点击事件
     */
    private void addClick() {
        //左上角返回图片的点击事件
        RxView.clicks(imgBack)
                .throttleFirst(700, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        dismiss();
                    }
                });
        //预约时段列表的点击相应
        ItemClickSupport.addTo(recyclerAppTime)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        selectTime = listTime.get(position);
                        //EventBus.getDefault().post(new PatientCardDialogEvent(patientCard));
                        EventBus.getDefault().post(new AppTimeEvent(selectTime));
                        dismiss();
                    }
                });
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        //设置全屏显示
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        //window.setGravity(Gravity.BOTTOM); //设置位于界面底部
        window.setDimAmount(1.0f);//设置透明度
        super.onResume();
    }

}
