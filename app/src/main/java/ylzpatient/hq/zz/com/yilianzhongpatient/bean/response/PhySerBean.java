package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

/**
 * Created by Administrator on 2016-8-25.
 * 体检套餐的实体
 */
public class PhySerBean {

    public int img; //套餐图片
    public String name; //套餐名称
    public String newPrice; //套餐现在的价格
    public String oldPrice; //套餐原价


    public PhySerBean() {
    }

    public PhySerBean(int img, String name, String newPrice, String oldPrice) {
        this.img = img;
        this.name = name;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }


    @Override
    public String toString() {
        return "PhySerBean{" +
                "img=" + img +
                ", name='" + name + '\'' +
                ", newPrice='" + newPrice + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                '}';
    }
}
