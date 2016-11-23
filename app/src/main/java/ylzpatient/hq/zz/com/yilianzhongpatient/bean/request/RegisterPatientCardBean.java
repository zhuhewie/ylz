package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-9-28.
 * v1
 * 用于创建新就诊卡的网络请求的实体
 * 存放患者输入的新建就诊卡的信息
 */
public class RegisterPatientCardBean {
    public String PatientName;//名字;
    public String Mobile;//手机号;
    public String PayType;//自费;
    public String IdCard;//身份证号;
    public String Sex;//性别;
    public String Birthday;//生日
    public String Address;//地址


    public String getPatientName() {
        return PatientName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    @Override
    public String toString() {
        return "RegisterPatientCardBean{" +
                "PatientName='" + PatientName + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", PayType='" + PayType + '\'' +
                ", IdCard='" + IdCard + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
