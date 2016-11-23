package ylzpatient.hq.zz.com.yilianzhongpatient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.functions.Action1;
import ylzpatient.hq.zz.com.yilianzhongpatient.fragment.manage.UserManager;
import ylzpatient.hq.zz.com.yilianzhongpatient.service.ChatService;

import static ylzpatient.hq.zz.com.yilianzhongpatient.utils.Uitls.getVersionCode;

public class LoadingActivity extends AppCompatActivity {

    @BindView(R.id.img_loading)
    ImageView imgLoading;
    @BindView(R.id.rl_loading)
    RelativeLayout rlLoading;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);
        //隐藏底部的虚拟导航栏,点击后就显示
        //imgLoading.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setScreen();
        testOrFormal();//设置版本,是测试还是正式
        GeneralConfig.setUser(UserManager.getInstance().getCurrentUser());

        init();
    }

    private void init() {
//        Intent startIntent = new Intent(this, MyService.class);
        startService(new Intent(this, ChatService.class));
        //设置loading图
        Picasso.with(LoadingActivity.this)
                .load(R.mipmap.loading)
                .tag(LoadingActivity.class.getName())
                .resize(GeneralConfig.screenWidth,GeneralConfig.screenHeight)
                .centerCrop()
                .into(imgLoading);

        //2500毫秒后启动主界面
        Observable.timer(2500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(new Intent(LoadingActivity.this, MainActivity2.class));
                        finish();
                    }
                });
    }

    /**
     * 获取屏幕宽高,并保存
     */
    private void setScreen() {
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;    //得到宽度
        GeneralConfig.screenWidth = width;
        Log.i(GeneralConfig.LOG_TAG,"屏幕宽度:" + width);
        int height = dm.heightPixels;  //得到高度
        //height = height + getNavigationBarHeight(LoadingActivity.this);
        Log.i(GeneralConfig.LOG_TAG,"屏幕高度:" + height);
        GeneralConfig.screenHeight = height;

    }

    /**
     * 判断是正式版本,还是测试版本
     * 正式版本为非零偶数
     * 测试版本为奇数,
     * 如果无法获取版本code的奇偶,按正式版本处理
     */
    private void testOrFormal() {
        int versionCode  = getVersionCode();
        if(versionCode % 2 == 0) {
            GeneralConfig.TEST = false; //正式版本
        } else {
            GeneralConfig.TEST = true;  //测试版本
        }
    }

//    /**
//     * 获取是否存在NavigationBar：
//     * @param context
//     * @return
//     */
//    private static boolean checkDeviceHasNavigationBar(Context context) {
//        if(isHighThanOrEqual4point0() == true){
//            Resources resources = context.getResources();
//            int resourceId = resources.getIdentifier("config_showNavigationBar", "bool", "android");
//            if (resourceId > 0) {
//                return resources.getBoolean(resourceId);
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 获取NavigationBar的高度
//     * @param context
//     * @return
//     */
//    private static int getNavigationBarHeight(Context context) {
//        int navigationBarHeight = 0;
//        Resources rs = context.getResources();
//        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
//        if (id > 0 && checkDeviceHasNavigationBar(context)) {
//            navigationBarHeight = rs.getDimensionPixelSize(id);
//        }
//        Log.i(GeneralConfig.LOG_TAG,"底部导航栏的高度:" + navigationBarHeight);
//        return navigationBarHeight;
//    }
//
//    //版本
//    public static boolean isHighThanOrEqual4point0(){
//        if(getAPIVersion() >= 14){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    //API版本号 
//    public static int getAPIVersion(){
//        int APIVersion;
//        try {
//            APIVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
//        } catch (NumberFormatException e) {
//            APIVersion = 0;
//        }
//        return APIVersion;
//    }
}
