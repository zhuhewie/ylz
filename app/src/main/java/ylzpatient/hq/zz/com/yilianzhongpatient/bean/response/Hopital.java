package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-9-14.
 */
public class Hopital implements Parcelable{


    /**
     * OrgId : HQ_1000
     * OrgName : 广州惠侨
     * OrgLogo : null
     * ServerUrl : http://192.168.1.211:8006/wm.aspx/
     * GradeLevel : 三甲医院
     */

    private String OrgId;
    private String OrgName;
    private String OrgLogo;
    private String ServerUrl;
    private String GradeLevel;

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String OrgId) {
        this.OrgId = OrgId;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String OrgName) {
        this.OrgName = OrgName;
    }

    public Object getOrgLogo() {
        return OrgLogo;
    }

    public void setOrgLogo(String OrgLogo) {
        this.OrgLogo = OrgLogo;
    }

    public String getServerUrl() {
        return ServerUrl;
    }

    public void setServerUrl(String ServerUrl) {
        this.ServerUrl = ServerUrl;
    }

    public String getGradeLevel() {
        return GradeLevel;
    }

    public void setGradeLevel(String GradeLevel) {
        this.GradeLevel = GradeLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(OrgId);
        parcel.writeString(OrgName);
        parcel.writeString(ServerUrl);
        parcel.writeString(GradeLevel);
        parcel.writeString(OrgLogo);
    }

    public static final Parcelable.Creator<Hopital> CREATOR = new Parcelable.Creator<Hopital>() {
        @Override
        public Hopital createFromParcel(Parcel in) {
            return new Hopital(in);
        }

        @Override
        public Hopital[] newArray(int size) {
            return new Hopital[size];
        }
    };
    public Hopital(Parcel in) {
        OrgId = in.readString();
        OrgName = in.readString();
        OrgLogo = in.readString();
        ServerUrl = in.readString();
        GradeLevel = in.readString();
    }

    public Hopital() {
    }

    @Override
    public String toString() {
        return "Hopital{" +
                "OrgId='" + OrgId + '\'' +
                ", OrgName='" + OrgName + '\'' +
                ", OrgLogo='" + OrgLogo + '\'' +
                ", ServerUrl='" + ServerUrl + '\'' +
                ", GradeLevel='" + GradeLevel + '\'' +
                '}';
    }
}
