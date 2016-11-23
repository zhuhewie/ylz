package ylzpatient.hq.zz.com.yilianzhongpatient.utils;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;

/**
 * Created by Administrator on 2016-8-31.
 *
 * 用于获取全局的context
 */
public class UtilApplication extends Application {
    private static Context context;
    protected static UtilApplication instance;
    @Override
    public void onCreate() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            AppLog.configure();
        }
        super.onCreate();
        Fresco.initialize(this);
        instance = this;
        //获取Context
        context = getApplicationContext();
        if (!GeneralConfig.TEST) {
           // Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程  以下用来捕获程序崩溃异常
        }
    }

    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            AppLog.logError("Application uncaughtException ",throwable);
            Log.e(GeneralConfig.LOG_TAG,thread.toString() + throwable.getMessage());
            restartApp();
        }
    };
    public void restartApp(){
//        Intent intent = new Intent(instance,MainActivity2.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        instance.startActivity(intent);
//        android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
        Intent intent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
        System.exit(0);
    }

    //返回
    public static Context getContextObject(){
        return context;
    }
}
