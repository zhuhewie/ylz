package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.event.ImageSelectorEvent;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.WaitImgAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.EnterClinicRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QueueRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QuitQueueRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ClinicBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.OnlineDoctorBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.PatientCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.QueueBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.MainSwitchEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.SwitchFragmentEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.CameraPictureDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.InClinicDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.onClik.ItemClickSupport;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;

/**
 * Created by Administrator on 2016-10-9.
 * 候诊界面
 */
public class WaitFragment extends SuperFragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.ll_quit_queue)
    LinearLayout llQuitQueue;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_wait_number)
    TextView tvWaitNumber;
    @BindView(R.id.tv_wait_time)
    TextView tvWaitTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_doctor_detial)
    TextView tvDoctorDetial;
    @BindView(R.id.btn_getin_room)
    Button btnGetinRoom;
    @BindView(R.id.et_symptom)
    EditText etSymptom;
    @BindView(R.id.img_add_img)
    ImageView imgAddImg;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;


    private static final int REQUEST_IMAGE = 2;
    private static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 110;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private static final int REQUEST_CAMERA = 100;
    private static final String ARG_QUEUE_REQUEST = "arg_queue_request"; //排队信息请求标签
    private static final String ARG_QUEUE_RESULT = "arg_queue_result";  //排队信息返回标签

    private OnlineDoctorBean chooseDoctor;
    private PatientCardBean patientCard;
    private QueueRequestBean queueRequest;//排队请求实体 由上个界面传入,千万不能new
    private QueueBean queueResult;//排队信息的返回体  由上个界面传入,千万不能new
    private QuitQueueRequestBean quitQueueRequest;//退出排队的请求体
    private EnterClinicRequestBean enterClinicRequest;//进入诊室的请求体
    private ClinicBean clinic;//进入诊室成功后的返回体

    private ArrayList<String> mSelectPath;
    private WaitImgAdapter waitImgAdapter;


    public static WaitFragment newInstance(QueueRequestBean queueRequest, QueueBean queueResult) {
        WaitFragment fragment = new WaitFragment();
        Bundle args = new Bundle();
        if (queueRequest != null) { //排队请求体不为null
            args.putParcelable(ARG_QUEUE_REQUEST, queueRequest);
        }
        if (queueResult != null) { //排队返回信息不为空
            args.putParcelable(ARG_QUEUE_RESULT, queueResult);
        }
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            queueRequest = bundle.getParcelable(ARG_QUEUE_REQUEST);
            queueResult = bundle.getParcelable(ARG_QUEUE_RESULT);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.o_wait_layout, container, false);
        ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);
        initView();
        addClick();

        loadingData();
        return v;
    }

    private void initView() {
        waitImgAdapter = new WaitImgAdapter(getContext());
        mSelectPath = new ArrayList<>();
        quitQueueRequest = new QuitQueueRequestBean();
        enterClinicRequest = new EnterClinicRequestBean();
        textViewName.setText("候诊");
        setWaitMes();

        GridLayoutManager glManager = new GridLayoutManager(getContext(), 4);
        glManager.setOrientation(OrientationHelper.VERTICAL); //设置垂直方向滑动的列表
        rvImg.setLayoutManager(glManager);
        rvImg.setItemAnimator(new DefaultItemAnimator()); //设置增加移除动画
        rvImg.setAdapter(waitImgAdapter);
    }

    /**
     * 界面显示的候诊信息
     */
    private void setWaitMes() {
        if (queueResult != null) {
            tvName.setText(queueResult.getPatientName());
            tvDoctorDetial.setText(queueResult.getDoctorName() + "   " + queueResult.getHospitalName() + "  "
                    + queueResult.getDepartmentName());
            tvNumber.setText(queueResult.getQueueNo() + "");
            tvWaitNumber.setText(queueResult.getWaitingCount() + "");
            tvWaitTime.setText(queueResult.getWaitingCount() * 3 + "");
        }
    }

    /**
     * 界面点击事件
     */
    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
        //进入诊室
        RxView.clicks(btnGetinRoom)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .flatMap(new Func1<Void, Observable<ResquestResult<ClinicBean>>>() {
                    @Override
                    public Observable<ResquestResult<ClinicBean>> call(Void aVoid) {
                        if (!Uitls.isEmptyClazz(queueResult)) {
                            enterClinicRequest.setQueueSn(queueResult.getQueueSn());
                            enterClinicRequest.setHospitalId(queueResult.getHospitalId());
                            enterClinicRequest.setDepartmentCode(queueResult.getDepartmentCode());
                            enterClinicRequest.setDepartmentName(queueResult.getDepartmentName());
                            enterClinicRequest.setDoctorCode(queueResult.getDoctorCode());
                            enterClinicRequest.setDoctorName(queueResult.getDoctorName());
                            enterClinicRequest.setPatientId(queueResult.getPatientId());
                            enterClinicRequest.setPatientName(queueResult.getPatientName());
                            enterClinicRequest.setUserId(UserManager.getInstance().getCurrentUser().getUserId());
                        }
                        return HttpMethods.getInstance().enterClinic(enterClinicRequest);
                    }
                })
                .subscribe(new Action1<ResquestResult<ClinicBean>>() {
                    @Override
                    public void call(ResquestResult<ClinicBean> clinicResult) {
                        if (!Uitls.isEmptyClazz(clinicResult)) {
                            if (TextUtils.isEmpty(clinicResult.ResultCode)) {
                                clinic = clinicResult.ResultData;
                                showDialog(InClinicDialog.newInstance());
                            } else {
                                Snackbar.make(llQuitQueue, clinicResult.ResultMsg, Snackbar.LENGTH_LONG).show();
                                Log.i(GeneralConfig.LOG_TAG, clinicResult.ResultMsg);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i(GeneralConfig.LOG_TAG, throwable.toString());
                        Snackbar.make(llQuitQueue, "进入诊室失败", Snackbar.LENGTH_LONG).show();

                    }
                });
        //添加图片点击
        RxView.clicks(imgAddImg)
                .throttleFirst(GeneralConfig.DOUBLE_HIT, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //Snackbar.make(imgAddImg, "添加图片", 1000).show();

                        //显示提示选择相册或者拍照的dialog
                        showDialog(CameraPictureDialog.newInstance(mSelectPath));

                        //pickImage(); //不经过dialog选择,直接跳转到图片选择的界面.在图片选择界面可以在调用相机照相
                    }
                });
        //预览图片列表的点击事件
        ItemClickSupport.addTo(rvImg)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position, long id) {
                        if (mSelectPath == null || position == mSelectPath.size()) {
                            //pickImage();
                            showDialog(CameraPictureDialog.newInstance(mSelectPath));
                        } else {
                            switchFatherContent(WaitFragment.this, PreImgFragment.newInstance(mSelectPath, position));
                        }
                    }
                });
        //退出排队
        RxView.clicks(llQuitQueue)
                .flatMap(new Func1<Void, Observable<ResquestResult>>() {
                    @Override
                    public Observable<ResquestResult> call(Void aVoid) {
                        if (queueResult != null) {
                            quitQueueRequest.setQueueSn(queueResult.getQueueSn());
                            return HttpMethods.getInstance().quitQueue(quitQueueRequest);
                        } else {
                            return Observable.just(null);
                        }
                    }
                })
                .subscribe(new Action1<ResquestResult>() {
                    @Override
                    public void call(ResquestResult result) {
                        if (!Uitls.isEmptyClazz(result)) {
                            if (TextUtils.isEmpty(result.ResultCode)) {//退出排队成功
                                getFragmentManager().popBackStack();//返回
                            }
                            Snackbar.make(llQuitQueue, result.ResultMsg, Snackbar.LENGTH_LONG).show();
                            Log.i(GeneralConfig.LOG_TAG, result.ResultMsg);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(GeneralConfig.LOG_TAG, "退出排队出现异常" + throwable.toString());
                    }
                });
    }

    /**
     * 联网加载排队信息
     */
    private void loadingData() {
        if (queueRequest != null && queueResult == null) { //联网获取候诊信息
            HttpMethods.getInstance().getQueue(new Subscriber<ResquestResult<QueueBean>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ResquestResult<QueueBean> httpQueueResult) {
                    if (httpQueueResult != null && TextUtils.isEmpty(httpQueueResult.ResultCode)) {
                        queueResult = httpQueueResult.ResultData;
                        setWaitMes();
                        Snackbar.make(textViewName, "排队成功" + httpQueueResult.ResultMsg, Snackbar.LENGTH_LONG).show();
                        Log.i(GeneralConfig.LOG_TAG, "排队成功" + httpQueueResult.ResultMsg);
                    } else {//排队失败
                        String faile = httpQueueResult == null ? "" : httpQueueResult.ResultMsg;
                        Snackbar.make(textViewName, "排队失败" + faile, Snackbar.LENGTH_LONG).show();
                        Log.i(GeneralConfig.LOG_TAG, "排队失败" + faile);
                    }
                }
            }, queueRequest);
        }
    }

    /**
     * 跳转到图库选择界面的方法
     */
    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            int maxNum = 8;
            MultiImageSelector selector = MultiImageSelector.create();
            selector.showCamera(true);// 是否显示相机. 默认为显示
            selector.count(maxNum);// 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
//            selector.single();// 单选模式
            selector.multi();//设置图库多选
            selector.origin(mSelectPath);// 默认已选择图片. 只有在选择模式为多选时有效
            selector.start(getActivity(), REQUEST_IMAGE);
        }
    }

    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
        }
    }

    /**
     * 接收数据
     *
     * @param event
     */
    @Subscribe
    public void imgSelector(ImageSelectorEvent event) {
        if (event.resultList != null && event.resultList.size() > 0) {
            mSelectPath = event.resultList;
        }
        if (!TextUtils.isEmpty(event.stringFile)) {
            mSelectPath.add(event.stringFile);
        }
        waitImgAdapter.setData(mSelectPath);
        waitImgAdapter.notifyDataSetChanged();

        //将获取到的数据转换,占时不用
//        StringBuilder sb = new StringBuilder();
//        for(String p: mSelectPath){
//            sb.append(p);
//            sb.append("\n");
//        }
//        Snackbar.make(imgAddImg, sb.toString(), 1000).show();
        //textView.setText(sb.toString());

    }

    @Subscribe
    public void inClinic(SwitchFragmentEvent event) {
        if (event.type == GeneralConfig.CLINIC_FRAGMENT && !Uitls.isEmptyClazz(clinic)) {
            queueResult.setClinicId(clinic.getClinicId());
            queueResult.setDoctorUserId(clinic.getDoctorUserId());
            queueResult.setDoctorAvatar(clinic.getDoctorAvatar());
            getFragmentManager().popBackStack();
            EventBus.getDefault().post(new MainSwitchEvent(ClinicFragment.newInstance(queueResult)));
//            switchFatherContent(this, ClinicFragment.newInstance(queueResult));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
