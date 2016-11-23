package ylzpatient.hq.zz.com.yilianzhongpatient.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 聊天的服务
 */
public class ChatService extends Service {
    public ChatService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
