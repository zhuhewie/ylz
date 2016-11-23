package ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.Service.onlineConsult;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_76;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.adapter.ChatAdapter;
import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.base.ResquestResult;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.QuitClinicRequestBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.SendChatBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.QueueBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.ReceiveChatBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.event.BackEvent;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.layout.dialog.GiveUpDialog;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.http.HttpMethods;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-10-12.
 * 诊室界面,或者和医生交流的界面
 */
public class ClinicFragment extends SuperFragment {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.rv_chat)
    RecyclerView rvChat;
    @BindView(R.id.et_send_text)
    EditText etSendText;
    @BindView(R.id.img_add_img)
    ImageView imgAddImg;
    @BindView(R.id.img_pic)
    ImageView imgPic;
    @BindView(R.id.img_camera)
    ImageView imgCamera;
    @BindView(R.id.img_back_chat)
    ImageView imgBackChat;
    @BindView(R.id.ll_choose_function)
    LinearLayout llChooseFunction;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.ll_chat)
    LinearLayout llChat;
    private static final String ARG_CLINIC = "arg_clinic";  //排队信息返回标签
    private static final String ARG_QUEUE = "arg_queue";  //

    private InputMethodManager imm;
    private ChatAdapter chatAdapter;
    private List<Object> list;
    //private ClinicBean clinic;
    private QueueBean queueMess;
    private QuitClinicRequestBean quitClinicReason;//退出诊室的请求体

    private WebSocketClient client;// 连接客户端
    private DraftInfo selectDraft;// 连接协议
    private Gson gson;

    public static ClinicFragment newInstance(QueueBean queueMess) {
        ClinicFragment fragment = new ClinicFragment();
        Bundle args = new Bundle();
//        args.putParcelable(ARG_CLINIC, clinic);
        args.putParcelable(ARG_QUEUE, queueMess);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        clinic = getArguments().getParcelable(ARG_CLINIC);
        queueMess = getArguments().getParcelable(ARG_QUEUE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chat_doctor_layout, container, false);
        ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);
        initView();

        addClick();
        connect();
        hideSoft();
        return v;
    }

    /**
     * 长连接,用于发送接收消息
     */
    private void connect() {
        try {
            Log.i("wlf", "连接地址：" + GeneralConfig.CHAT_URL);
            /*
                第一个参数是连接地址,第二个参数是协议,使用的是WebSocket协议Draft_76
                其他协议:WebSocket协议Draft_17
                        WebSocket协议Draft_10
                        WebSocket协议Draft_75
             */
            client = new WebSocketClient(new URI(GeneralConfig.CHAT_URL), new Draft_76()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.e("wlf", "已经连接到服务器【" + getURI() + "】");
                }

                @Override
                public void onMessage(String message) {
                    Log.i(GeneralConfig.LOG_TAG,"接收到的消息"+message);
                    //接收到的消息,使用rxjava把数据传递到主线程
                    Observable.just(message)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<String>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e(GeneralConfig.LOG_TAG, e.toString());

                                }

                                @Override
                                public void onNext(String s) {
                                    messageProcess(s);
                                }
                            });
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.e("wlf", "断开服务器连接【" + getURI() + "，状态码： " + code + "，断开原因：" + reason + "】");
                }

                @Override
                public void onError(Exception ex) {
                    Log.e("wlf", "连接发生了异常【异常原因：" + ex + "】");
                }
            };
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(GeneralConfig.LOG_TAG, "聊天长连接异常:" + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(GeneralConfig.LOG_TAG, "聊天长连接异常:" + e.toString());
        }
        //登录
    }

    private void hideSoft() {
        textViewName.requestFocus();
        //初次进入界面时,隐藏键盘并且输入框没有焦点
        imm.hideSoftInputFromWindow(textViewName.getWindowToken(), 0); //强制隐藏键盘
        llChooseFunction.setVisibility(View.GONE); //隐藏图片选择界面

    }

    //初始化视图
    private void initView() {
        list = new ArrayList<>();
        gson = new Gson();
        chatAdapter = new ChatAdapter(getContext(), list);
        quitClinicReason = new QuitClinicRequestBean();

        tvTip.setText("您已经入"+queueMess.getDoctorName() +"医生的网络诊室");
        textViewName.setText(queueMess.getDoctorName()+"医生的诊室");

        //1.得到InputMethodManager对象
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //2.调用showSoftInput方法显示软键盘，其中view为聚焦的view组件
        //imm.showSoftInput(view,InputMethodManager.SHOW_FORCED)

        //3.调用hideSoftInputFromWindow方法隐藏软键盘
        //imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

        //4.获取状态信息
        //boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开

        //体检套餐列表
        LinearLayoutManager llManager = new LinearLayoutManager(getContext());
        llManager.setOrientation(LinearLayoutManager.VERTICAL); //纵向滑动
        rvChat.setLayoutManager(llManager);
        rvChat.setAdapter(chatAdapter);

    }

    //点击事件
    private void addClick() {
        //返回
        RxView.clicks(imgBack)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getFragmentManager().popBackStack();
//                        showDialog(RatingDialog.newInstance()); //p评价的dialog.医生关闭给你的聊天时,弹出
                    }
                });
        //加号图片的点击事件
        RxView.clicks(imgAddImg)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        input(llChooseFunction.getVisibility() == View.VISIBLE);
                    }
                });
        //发送按钮的点击事件
        RxView.clicks(tvSend)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        String s = etSendText.getText().toString();
                        if (TextUtils.isEmpty(s)) {
//                            Snackbar.make(tvSend, "发送内容不能为空", Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(UtilApplication.getContextObject(), "发送内容不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            SendChatBean text = new SendChatBean();
                            text.setMsg(s);
                            text.setTime((int) System.currentTimeMillis());
                            text.setFromId(UserManager.getInstance().getCurrentUser().getUserId());
                            text.setToId(queueMess.getDoctorUserId());
                            text.setClinicId(queueMess.getClinicId());
                            text.setPatientId(queueMess.getPatientId());
//
                            list.add(text);
                            chatAdapter.notifyDataSetChanged();
                            rvChat.smoothScrollToPosition(list.size());//滑动到底部
                            sendTextMessage("MESSAGE "+gson.toJson(text)); //发送文字
//
                            etSendText.setText(" ");
//
                            Log.i(GeneralConfig.LOG_TAG, "发送内容:" + s);
                        }

                    }
                });
//        //输入框的点击事件
//        RxView.clicks(etSendText)
//                .subscribe(new Action1<Void>() {
//                    @Override
//                    public void call(Void aVoid) {
//                        input(true);
//                    }
//                });
        /*
        输入框焦点获取监听
         */
        RxView.focusChanges(etSendText)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        input(aBoolean);
                    }
                });
        //内容输入框的内容变化监听
        RxTextView.afterTextChangeEvents(etSendText)
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                        //如果有输入框有内容,就显示发送按钮,隐藏图片框
                        if (!TextUtils.isEmpty(etSendText.getText().toString())) {
                            imgAddImg.setVisibility(View.GONE);
                            tvSend.setVisibility(View.VISIBLE);
                        } else { //否则就隐藏发送按钮,显示图片框
                            imgAddImg.setVisibility(View.VISIBLE);
                            tvSend.setVisibility(View.GONE);
                        }
                    }
                });
        //放弃问诊
        RxView.clicks(imgBackChat)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        showDialog(GiveUpDialog.newInstance());
                    }
                });
    }

    /**
     * 是否显示软键盘 并且输入文本框获取焦点
     *
     * @param inputType = ture 显示软键盘,并让edittext获取焦点
     * @param inputType = false 新仓软键盘,并让edittext获取失去
     */
    private void input(boolean inputType) {
        if (inputType) {
            llChooseFunction.setVisibility(View.GONE); //隐藏图片选择界面
            etSendText.requestFocus(); //获取焦点
            imm.showSoftInput(etSendText, InputMethodManager.SHOW_FORCED); //显示软键盘
            rvChat.smoothScrollToPosition(list.size());//滑动到底部
        } else {
            //隐藏软键盘
            imm.hideSoftInputFromWindow(imgAddImg.getWindowToken(), 0); //强制隐藏键盘
            //显示图片选择view
            llChooseFunction.setVisibility(View.VISIBLE);
            etSendText.clearFocus(); //失去焦点
        }
    }

    //判断软键盘是否弹出
//    private boolean isKeyboardShown(View rootView) {
//        final int softKeyboardHeight = 100;
//        Rect r = new Rect();
//        rootView.getWindowVisibleDisplayFrame(r);
//        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
//        int heightDiff = rootView.getBottom() - r.bottom;
//        return heightDiff > softKeyboardHeight * dm.density;
//    }

    @Subscribe
    public void back(BackEvent event) {
        //接收activity返回键,重写返回键功能
//        if (event.type == GeneralConfig.CLINIC_FRAGMENT) {
//            showDialog(GiveUpDialog.newInstance());
//        }
//        else
//        //接收放弃诊室dialog的确定点击事件,结束诊室界面
        if (event.type == 11) {
            quitClinicReason.setClinicId(queueMess.getClinicId());
            quitClinicReason.setReason(event.backReason.toString());
            HttpMethods.getInstance().quitClinic(new Subscriber<ResquestResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Log.i(GeneralConfig.LOG_TAG, "退出诊室异常");
                }

                @Override
                public void onNext(ResquestResult result) {
                    if(!Uitls.isEmptyClazz(result)) {
                        if (TextUtils.isEmpty(result.ResultCode)) {
                            //退出诊室成功
                            getFragmentManager().popBackStack();
                        } else {
                            Snackbar.make(textViewName, "退出诊室失败" + result.ResultMsg, Snackbar.LENGTH_LONG).show();
                            Log.i(GeneralConfig.LOG_TAG, "退出诊室失败"+ result.ResultMsg);
                        }
                    } else {
                        //退出申请没响应
                            Snackbar.make(textViewName, "退出诊室没有响应", Snackbar.LENGTH_LONG).show();
                            Log.i(GeneralConfig.LOG_TAG, "退出诊室没有响应");
                    }
                }
            }, quitClinicReason);
        }
    }


    /**
     * 发送消息
     *
     * @param payload
     */
    public void sendTextMessage(String payload) {
        //connect();
        try {
            if (client != null) {
                Log.i(GeneralConfig.LOG_TAG, "WS发送信息：" + payload);
                client.send(payload);
            } else {
                //throw new Exception(new Exception("not network"));
                //Snackbar.make(textViewName,"连接已经中断",Snackbar.LENGTH_LONG).show();
                connect();
                if (client != null) {
                    Log.i(GeneralConfig.LOG_TAG, "WS发送信息：" + payload);
                    client.send(payload);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理接收到的消息
     *
     * @param message
     */
    private void messageProcess(String message) {
        String[] mess = message.split(" ");
        String type = mess[0]; //标题
        if (!type.equals(message)) {
            String textString = mess[mess.length-1]; //接收到的内容
            if ("LOGIN".equals(type)) {
                Log.i(GeneralConfig.LOG_TAG, "登录成功");
                //4:接收到的聊天消息
            } else if ("MESSAGE".equals(type)) {
                try {
                    Log.i(GeneralConfig.LOG_TAG, "接收到消息:" + textString);
                    ReceiveChatBean receiveChat = gson.fromJson(textString, ReceiveChatBean.class);
                    if (!Uitls.isEmptyClazz(receiveChat)) { //如果内容解析成功
                        list.add(receiveChat);
                        chatAdapter.notifyDataSetChanged();
                        rvChat.smoothScrollToPosition(list.size());//滑动到底部
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    Log.e(GeneralConfig.LOG_TAG, "消息转换为bean失败!消息内容:" + textString);

                }
            }
        }
        //1:消息为空
        else if (TextUtils.isEmpty(message)) {
            Log.i(GeneralConfig.LOG_TAG, "收到的信息为空");
            //2:消息是连接成功后的返回
        } else if (message.equals("CONNECTED")) {
            sendTextMessage("LOGIN {\"userId\":\""+UserManager.getInstance().getCurrentUser().getUserId()+"\",\"type\":0}");

            Log.i(GeneralConfig.LOG_TAG, "接收到连接成功标志");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class DraftInfo {

        private final String draftName;
        private final Draft draft;

        public DraftInfo(String draftName, Draft draft) {
            this.draftName = draftName;
            this.draft = draft;
        }

        @Override
        public String toString() {
            return draftName;
        }
    }

    private class ServerInfo {

        private final String serverName;
        private final String serverAddress;

        public ServerInfo(String serverName, String serverAddress) {
            this.serverName = serverName;
            this.serverAddress = serverAddress;
        }

        @Override
        public String toString() {
            return serverName;
        }
    }
}
