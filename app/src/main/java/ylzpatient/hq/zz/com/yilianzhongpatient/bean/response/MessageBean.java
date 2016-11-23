package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-8-25.
 * 咨询的bean
 */
public class MessageBean {
    public int messageImg; //图片
    public String messageTittle; //消息标题
    public String messageContext; //消息的内容


    public MessageBean() {
    }

    public MessageBean(int messageImg, String messageTittle, String messageContext) {
        this.messageImg = messageImg;
        this.messageTittle = messageTittle;
        this.messageContext = messageContext;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "messageImg=" + messageImg +
                ", messageTittle='" + messageTittle + '\'' +
                ", messageContext='" + messageContext + '\'' +
                '}';
    }
}
