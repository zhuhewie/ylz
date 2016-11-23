package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-11-14.
 * 健康档案基本信息的实体
 */

public class HealthBasicBean {

    /**
     * Name : sample string 1
     * Sex : sample string 2
     * Age : 3
     * Weight : sample string 4
     * Blood : sample string 5
     * Allergies : sample string 6
     * Family : sample string 7
     * Drinking : sample string 8
     * Smoking : sample string 9
     * Medical : sample string 10
     */

    private String Name;    //姓名
    private String Sex;     //性别
    private int Age;        //年龄
    private String Weight;  //体重
    private String Blood;   //血型
    private String Allergies;//过敏史
    private String Family;  //家族史
    private String Drinking;//饮酒史
    private String Smoking; //吸烟史
    private String Medical; //用药史

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String Blood) {
        this.Blood = Blood;
    }

    public String getAllergies() {
        return Allergies;
    }

    public void setAllergies(String Allergies) {
        this.Allergies = Allergies;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String Family) {
        this.Family = Family;
    }

    public String getDrinking() {
        return Drinking;
    }

    public void setDrinking(String Drinking) {
        this.Drinking = Drinking;
    }

    public String getSmoking() {
        return Smoking;
    }

    public void setSmoking(String Smoking) {
        this.Smoking = Smoking;
    }

    public String getMedical() {
        return Medical;
    }

    public void setMedical(String Medical) {
        this.Medical = Medical;
    }
}
