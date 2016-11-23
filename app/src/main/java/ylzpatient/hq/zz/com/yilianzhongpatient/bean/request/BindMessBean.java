package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-25.
 * 绑定就诊卡的请求实体
 */

public class BindMessBean {

    /**
     * KeySearchType : 2
     * KeyWord : 0264745
     * SecondaryKey : 欧阳启明
     */

    private int KeySearchType;
    private String KeyWord;
    private String SecondaryKey;

    public int getKeySearchType() {
        return KeySearchType;
    }

    public void setKeySearchType(int KeySearchType) {
        this.KeySearchType = KeySearchType;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.KeyWord = KeyWord;
    }

    public String getSecondaryKey() {
        return SecondaryKey;
    }

    public void setSecondaryKey(String SecondaryKey) {
        this.SecondaryKey = SecondaryKey;
    }

    @Override
    public String toString() {
        return "BindMessBean{" +
                "KeySearchType=" + KeySearchType +
                ", KeyWord='" + KeyWord + '\'' +
                ", SecondaryKey='" + SecondaryKey + '\'' +
                '}';
    }
}
