package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.AddPatientCerdBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.BindMessBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.BindCardBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.Hopital;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.UserTestBean_2;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-10-18.
 * 绑定就诊卡界面
 */

public class BindCardFragment extends SuperFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.img_function)
    ImageView imgFunction;
    @BindView(R.id.tiet_card_number)
    TextInputEditText tietCardNumber;
    @BindView(R.id.til_card_number)
    TextInputLayout tilCardNumber;
    @BindView(R.id.sp_hos)
    AppCompatSpinner spHos;
    @BindView(R.id.tiet_card_type)
    TextInputEditText tietCardType;
    @BindView(R.id.til_card_type)
    TextInputLayout tilCardType;
    @BindView(R.id.tiet_name)
    TextInputEditText tietName;
    @BindView(R.id.til_name)
    TextInputLayout tilName;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.tv_hos)
    TextView tvHos;
    @BindView(R.id.tiet_phone)
    TextInputEditText tietPhone;
    @BindView(R.id.til_phone)
    TextInputLayout tilPhone;

    private BindMessBean bindMess;

    private List<Hopital> hosList;//医院列表 ,包含医院名称id,logo等信息
    private List<String> stringList;//医院名称列表,只有医院的名字

    private ArrayAdapter arrayAdapter;
    private Hopital selectHospital;

    public static BindCardFragment newInstance() {
        BindCardFragment fragment = new BindCardFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.m_new_card_layout, container, false);

        ButterKnife.bind(this, v);

        initView();

        addClick();
        getHosOrg();
        return v;
    }

    /**
     * 界面的点击事件
     */
    private void addClick() {
        //返回图标
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
                    }
                });
//        //确定按钮
//        RxView.clicks(btnOk)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//                        bindMess.setKeyWord(tietCardNumber.getText().toString());
//                        bindMess.setSecondaryKey(tietName.getText().toString());
//                        bindMess.setKeySearchType(2);
//                        bindCard();
//                    }
//                });
        //医院选择监听
        spHos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectHospital = hosList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //医院列表的点击事件
        RxView.clicks(tvHos)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Snackbar.make(tvHos, "重新获取医院列表中,请稍后...", Snackbar.LENGTH_SHORT).show();
                        getHosOrg();
                    }
                });
        RxView.clicks(btnOk)
                .throttleFirst(1, TimeUnit.SECONDS)
                //1:提交绑定信息到his
                .flatMap(new Func1<Void, Observable<BindCardBean>>() {
                    @Override
                    public Observable<BindCardBean> call(Void aVoid) {
                        if (checkInformation()) {
                            bindMess.setKeyWord(tietCardNumber.getText().toString());
                            bindMess.setSecondaryKey(tietName.getText().toString());
                            bindMess.setKeySearchType(2);
                            return HttpMethods.getInstance().bindCard_2(bindMess);
                        } else {
                            return Observable.just(null);
                        }
                    }
                })
                //2:同步绑定就诊卡数据到hos
                .flatMap(new Func1<BindCardBean, Observable<ResquestResult>>() {
                    @Override
                    public Observable<ResquestResult> call(BindCardBean bindCardBean) {
                        UserTestBean_2 user = UserManager.getInstance().getCurrentUser();
                        //绑定就诊卡的返回不能为空,登录信息不能为空,选中的医院不能为空

                        if (!Uitls.isEmptyClazz(bindCardBean) && !Uitls.isEmptyClazz(user)
                                && !Uitls.isEmptyClazz(selectHospital)) {

                            //封装上传信息,以便于提交到hos
                            AddPatientCerdBean card = new AddPatientCerdBean();
                            card.setUserId(user.getUserId());
                            card.setPatientId(bindCardBean.getPatientID());
                            card.setHospitalId(selectHospital.getOrgId());
                            card.setName(tietName.getText().toString());
                            card.setMobile(tietPhone.getText().toString());
                            card.setSocialNo(tietCardType.getText().toString());
                            card.setSex(bindCardBean.getSex());
                            card.setBirthday(getBrith(tietCardType.getText().toString()));
                            card.setCardNo(tietCardNumber.getText().toString());
                            //联网同步数据到hos
                            return HttpMethods.getInstance().addResquestObservable(card);
                        } else {
                            return Observable.just(null);
                        }
                    }
                })
                .subscribe(new Action1<ResquestResult>() {
                    @Override
                    public void call(ResquestResult result) {
                        if (!Uitls.isEmptyClazz(result) && TextUtils.isEmpty(result.ResultCode)) {
                            Snackbar.make(btnOk, "绑定成功", Snackbar.LENGTH_LONG).show();
                        } else if (result != null) {
                            Snackbar.make(btnOk, "绑定失败" + result.ResultMsg, Snackbar.LENGTH_LONG).show();
                        } else {
                            //Snackbar.make(btnOk, "绑定失败", Snackbar.LENGTH_LONG).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Snackbar.make(btnOk, "绑定失败", Snackbar.LENGTH_LONG).show();
                        Log.e(GeneralConfig.LOG_TAG, throwable.toString());
                    }
                });
    }

    /**
     * 初始化界面
     */
    private void initView() {
        hosList = new ArrayList<>();
        stringList = new ArrayList<>();
        bindMess = new BindMessBean();
        textViewName.setText("绑定就诊卡");
        arrayAdapter = new ArrayAdapter(UtilApplication.getContextObject(),
                R.layout.new_card_spinner_item, R.id.tv_hospital, stringList);
        spHos.setAdapter(arrayAdapter);
    }

    /**
     * 绑定就诊卡
     */
    private void bindCard() {
        if (!TextUtils.isEmpty(bindMess.getSecondaryKey()) && !TextUtils.isEmpty(bindMess.getKeyWord())) {
            //绑定并新建就诊卡
            HttpMethods.getInstance().bindAndNewCard(new Subscriber<ResquestResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Snackbar.make(btnOk, "输入信息有误,请重新输入", 3000).show();
                    Log.e(GeneralConfig.LOG_TAG, "绑定就诊卡时出现异常" + e.toString());
                }

                @Override
                public void onNext(ResquestResult result) {
                    if (TextUtils.isEmpty(result.ResultCode)) {
                        getFragmentManager().popBackStack();
                    }
                    Snackbar.make(btnOk, result.ResultMsg, 3000).show();
                    Log.e(GeneralConfig.LOG_TAG, result.ResultMsg);
                }
            }, bindMess, selectHospital, null);
        } else {
            Snackbar.make(btnOk, "就诊卡号,姓名不能为空", 3000).show();
        }
    }

    //获取所属医院列表
    private void getHosOrg() {
        HttpMethods.getInstance().getHosOrg(new Subscriber<List<Hopital>>() {
            @Override
            public void onCompleted() {
                for (Hopital h : hosList) {
                    stringList.add(h.getOrgName());
                }
                arrayAdapter.notifyDataSetChanged();
//                spHos.setSelection(0,true);
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(spHos, "获取医院列表出现异常", 3000).show();
                Log.e(GeneralConfig.LOG_TAG, "获取所属医院出现异常:" + e.toString());
            }

            @Override
            public void onNext(List<Hopital> hopitals) {
                if (hopitals != null && hopitals.size() > 0) {
                    Log.i(GeneralConfig.LOG_TAG, "获取所属医院成功");
                    hosList.clear();
                    hosList.addAll(hopitals);
                }
            }
        });
    }

    /**
     * 根据身份证号,剪切出来出生日期
     */
    private String getBrith(String idCard) {
        String brith = "1970-01-01 00:00:00";
        if (!TextUtils.isEmpty(idCard) && idCard.length() == 18) {//18位身份证
            brith = idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14) + " 00:00:00";
        }
        if(!TextUtils.isEmpty(idCard) && idCard.length() == 15){//15位身份证
            brith = "19"+idCard.substring(6,8) + "-" + idCard.substring(8, 10) + "-" + idCard.substring(10, 12) + " 00:00:00";
        }
        return brith;
    }

    /**
     * 检查绑定就诊卡的输入信息
     * @return
     */
    private boolean checkInformation() {
        String s = "";
        boolean result = false;
        if (TextUtils.isEmpty(tietCardNumber.getText().toString())) {
            s += "就诊卡号不能为空!";
        } else if (TextUtils.isEmpty(tietCardType.getText().toString())) {
            s += "证件号码不能为空!";
        } else if (tietCardType.getText().toString().length()!=15 ||tietCardType.getText().toString().length()!=18){
            s += "身份证号码输入错误!";
        } else if (TextUtils.isEmpty(tietName.getText().toString())) {
            s += "姓名不能为空!";
        } else if (TextUtils.isEmpty(tietPhone.getText().toString())){
            s += "手机号不能为空";
        } else if (!tietPhone.getText().toString().matches("[1]\\d{10}")){
            s += "手机号格式错误";
        }
        else {
            result = true;
        }
        if (!result) {
            Snackbar.make(btnOk,s,Snackbar.LENGTH_LONG).show();
        }
        return result;
    }
}
