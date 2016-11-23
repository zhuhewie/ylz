package ylzpatient.hq.zz.com.yilianzhongpatient.utils;

import android.os.Environment;

import com.github.snowdream.android.util.Log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * Created by Administrator on 2016/10/18.
 */

public class AppLog {
	
	//	if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
	//		AppLog.configure();
	//	}
	//  AppLog.logError("Application uncaughtException ",ex);

    public static void configure() {
		String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "HOS" +
						File.separator + "Common" + File.separator + "logs";
        Log.setEnabled(true);
        Log.setTag("TAG");
        createFileDir(path);
        LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(path+ File.separator + "log4j.txt");
        logConfigurator.setRootLevel(Level.WARN);
        // 设置日志输出级别
        logConfigurator.setLevel("org.apache", Level.WARN);
        //设置 输出到日志文件的文字格式 默认 %d %-5p [%c{2}]-[%L] %m%n
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        //设置输出到控制台的文字格式 默认%m%n
        logConfigurator.setLogCatPattern("%m%n");
        //设置总文件大小
        logConfigurator.setMaxFileSize(1024 * 1024 * 5);
        //设置最大产生的文件个数
        logConfigurator.setMaxBackupSize(1);
        //设置所有消息是否被立刻输出 默认为true,false 不输出
        logConfigurator.setImmediateFlush(true);
        //是否本地控制台打印输出 默认为true ，false不输出
        logConfigurator.setUseLogCatAppender(true);
        //设置是否启用文件附加,默认为true。false为覆盖文件
        logConfigurator.setUseFileAppender(true);
        //设置是否重置配置文件，默认为true
        logConfigurator.setResetConfiguration(true);
        //是否显示内部初始化日志,默认为false
        logConfigurator.setInternalDebugging(false);

        logConfigurator.configure();
    }

    public static void logWarn(String msg){
        //初始化 log
        Logger log=Logger.getLogger(AppLog.class);
        log.warn(msg);
    }

    public static void logError(String msg,Throwable r){
        //初始化 log
        Logger log=Logger.getLogger(AppLog.class);
        //写 info 日志
        log.error(msg,r);
    }

    public static boolean createFileDir(String FileFatherPath) {
        boolean flag = true;

        try {
            File e = new File(FileFatherPath);
            if(!e.exists()) {
                flag = e.mkdirs();
            }
        } catch (Exception var3) {
            flag = false;
        }

        return flag;
    }

}
