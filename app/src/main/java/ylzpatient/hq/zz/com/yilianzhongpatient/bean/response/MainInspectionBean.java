package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-11-4.
 * 获取到的检验报告的实体
 */

public class MainInspectionBean implements Parcelable {

    /**
     * sn : 102072016110120
     * repID : 102072016110120
     * ApplyID :
     * ReportName : 血液二项
     * ReportCode : 102072016110120
     * LisTime : 2016/11/1 10:27:21
     * RptTime : 2016-11-01 10:34
     * CheckItemList : null
     * DrugSensCheckItemList : null
     * SmearCheckItemList : null
     * LisRptDiag : null
     * ReportTypeValue : 1
     * ReportType : 1
     * ImgReportList : null
     * TreatDeptCode : null
     * TreatDeptName : null
     */

    private String sn;
    private String repID;
    private String ApplyID;
    private String ReportName;
    private String ReportCode;
    private String LisTime;
    private String RptTime;
    private Object CheckItemList;
    private Object DrugSensCheckItemList;
    private Object SmearCheckItemList;
    private Object LisRptDiag;
    private int ReportTypeValue;
    private int ReportType;
    private Object ImgReportList;
    private Object TreatDeptCode;
    private Object TreatDeptName;

    protected MainInspectionBean(Parcel in) {
        sn = in.readString();
        repID = in.readString();
        ApplyID = in.readString();
        ReportName = in.readString();
        ReportCode = in.readString();
        LisTime = in.readString();
        RptTime = in.readString();
        ReportTypeValue = in.readInt();
        ReportType = in.readInt();
    }

    public static final Creator<MainInspectionBean> CREATOR = new Creator<MainInspectionBean>() {
        @Override
        public MainInspectionBean createFromParcel(Parcel in) {
            return new MainInspectionBean(in);
        }

        @Override
        public MainInspectionBean[] newArray(int size) {
            return new MainInspectionBean[size];
        }
    };

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getRepID() {
        return repID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public String getApplyID() {
        return ApplyID;
    }

    public void setApplyID(String ApplyID) {
        this.ApplyID = ApplyID;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String ReportName) {
        this.ReportName = ReportName;
    }

    public String getReportCode() {
        return ReportCode;
    }

    public void setReportCode(String ReportCode) {
        this.ReportCode = ReportCode;
    }

    public String getLisTime() {
        return LisTime;
    }

    public void setLisTime(String LisTime) {
        this.LisTime = LisTime;
    }

    public String getRptTime() {
        return RptTime;
    }

    public void setRptTime(String RptTime) {
        this.RptTime = RptTime;
    }

    public Object getCheckItemList() {
        return CheckItemList;
    }

    public void setCheckItemList(Object CheckItemList) {
        this.CheckItemList = CheckItemList;
    }

    public Object getDrugSensCheckItemList() {
        return DrugSensCheckItemList;
    }

    public void setDrugSensCheckItemList(Object DrugSensCheckItemList) {
        this.DrugSensCheckItemList = DrugSensCheckItemList;
    }

    public Object getSmearCheckItemList() {
        return SmearCheckItemList;
    }

    public void setSmearCheckItemList(Object SmearCheckItemList) {
        this.SmearCheckItemList = SmearCheckItemList;
    }

    public Object getLisRptDiag() {
        return LisRptDiag;
    }

    public void setLisRptDiag(Object LisRptDiag) {
        this.LisRptDiag = LisRptDiag;
    }

    public int getReportTypeValue() {
        return ReportTypeValue;
    }

    public void setReportTypeValue(int ReportTypeValue) {
        this.ReportTypeValue = ReportTypeValue;
    }

    public int getReportType() {
        return ReportType;
    }

    public void setReportType(int ReportType) {
        this.ReportType = ReportType;
    }

    public Object getImgReportList() {
        return ImgReportList;
    }

    public void setImgReportList(Object ImgReportList) {
        this.ImgReportList = ImgReportList;
    }

    public Object getTreatDeptCode() {
        return TreatDeptCode;
    }

    public void setTreatDeptCode(Object TreatDeptCode) {
        this.TreatDeptCode = TreatDeptCode;
    }

    public Object getTreatDeptName() {
        return TreatDeptName;
    }

    public void setTreatDeptName(Object TreatDeptName) {
        this.TreatDeptName = TreatDeptName;
    }

    @Override
    public String toString() {
        return "MainInspectionBean{" +
                "sn='" + sn + '\'' +
                ", repID='" + repID + '\'' +
                ", ApplyID='" + ApplyID + '\'' +
                ", ReportName='" + ReportName + '\'' +
                ", ReportCode='" + ReportCode + '\'' +
                ", LisTime='" + LisTime + '\'' +
                ", RptTime='" + RptTime + '\'' +
                ", CheckItemList=" + CheckItemList +
                ", DrugSensCheckItemList=" + DrugSensCheckItemList +
                ", SmearCheckItemList=" + SmearCheckItemList +
                ", LisRptDiag=" + LisRptDiag +
                ", ReportTypeValue=" + ReportTypeValue +
                ", ReportType=" + ReportType +
                ", ImgReportList=" + ImgReportList +
                ", TreatDeptCode=" + TreatDeptCode +
                ", TreatDeptName=" + TreatDeptName +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sn);
        dest.writeString(repID);
        dest.writeString(ApplyID);
        dest.writeString(ReportName);
        dest.writeString(ReportCode);
        dest.writeString(LisTime);
        dest.writeString(RptTime);
        dest.writeInt(ReportTypeValue);
        dest.writeInt(ReportType);
    }


}
