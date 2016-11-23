package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016-11-7.
 * 检查报告的实体
 */

public class CheckBean {

    /**
     * sn : 0
     * repID : 970302
     * ApplyID : 23288212
     * ReportCode : 99
     * ReportName : 胸部正侧位
     * TimeList : null
     * BdChkRptDiag : null
     * BdCheckRptType : 1
     * Patient : null
     * ImgReportList : null
     * RisTime : 2016-11-01
     * RptTime : 2016-11-01
     * CheckItemList : [{"SerialNum":0,"PatientID":null,"InPatientNo":null,"AdmissTimes":0,"CheckPart":"胸部正侧位","CheckName":"","ImageSeen":"                                           胸部正侧位片    rnrn    两侧胸廓对称，所示肋骨未见明确异常征象，双肺野清晰，纹理走行自然，未见增强、紊乱。肺实质未见异常密度影或占位病变。双肺门不大，位置及密度正常。气管居中，纵隔未见增宽、移位。心影形态、大小正常。双肋膈角锐利。","ImageResult":"心肺膈肋未见异常。","ReportTime":"2016/11/1 10:20:26","ApplyTime":"2016-11-01","PrintFlag":0,"ReportURL":"http://120.196.145.38:8080/2016/20161101/426388.970302/970302.jpg","Reportime":null}]
     */

    private int sn;
    private String repID;
    private String ApplyID;
    private String ReportCode;
    private String ReportName;
    private Object TimeList;
    private Object BdChkRptDiag;
    private int BdCheckRptType;
    private Object Patient;
    private Object ImgReportList;
    private String RisTime;
    private String RptTime;
    /**
     * SerialNum : 0
     * PatientID : null
     * InPatientNo : null
     * AdmissTimes : 0
     * CheckPart : 胸部正侧位
     * CheckName :
     * ImageSeen :                                            胸部正侧位片    rnrn    两侧胸廓对称，所示肋骨未见明确异常征象，双肺野清晰，纹理走行自然，未见增强、紊乱。肺实质未见异常密度影或占位病变。双肺门不大，位置及密度正常。气管居中，纵隔未见增宽、移位。心影形态、大小正常。双肋膈角锐利。
     * ImageResult : 心肺膈肋未见异常。
     * ReportTime : 2016/11/1 10:20:26
     * ApplyTime : 2016-11-01
     * PrintFlag : 0
     * ReportURL : http://120.196.145.38:8080/2016/20161101/426388.970302/970302.jpg
     * Reportime : null
     */

    private List<CheckItemListBean> CheckItemList;

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
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

    public String getReportCode() {
        return ReportCode;
    }

    public void setReportCode(String ReportCode) {
        this.ReportCode = ReportCode;
    }

    public String getReportName() {
        return ReportName;
    }

    public void setReportName(String ReportName) {
        this.ReportName = ReportName;
    }

    public Object getTimeList() {
        return TimeList;
    }

    public void setTimeList(Object TimeList) {
        this.TimeList = TimeList;
    }

    public Object getBdChkRptDiag() {
        return BdChkRptDiag;
    }

    public void setBdChkRptDiag(Object BdChkRptDiag) {
        this.BdChkRptDiag = BdChkRptDiag;
    }

    public int getBdCheckRptType() {
        return BdCheckRptType;
    }

    public void setBdCheckRptType(int BdCheckRptType) {
        this.BdCheckRptType = BdCheckRptType;
    }

    public Object getPatient() {
        return Patient;
    }

    public void setPatient(Object Patient) {
        this.Patient = Patient;
    }

    public Object getImgReportList() {
        return ImgReportList;
    }

    public void setImgReportList(Object ImgReportList) {
        this.ImgReportList = ImgReportList;
    }

    public String getRisTime() {
        return RisTime;
    }

    public void setRisTime(String RisTime) {
        this.RisTime = RisTime;
    }

    public String getRptTime() {
        return RptTime;
    }

    public void setRptTime(String RptTime) {
        this.RptTime = RptTime;
    }

    public List<CheckItemListBean> getCheckItemList() {
        return CheckItemList;
    }

    public void setCheckItemList(List<CheckItemListBean> CheckItemList) {
        this.CheckItemList = CheckItemList;
    }

    public static class CheckItemListBean implements Parcelable {
        private int SerialNum;
        private Object PatientID;
        private Object InPatientNo;
        private int AdmissTimes;
        private String CheckPart;
        private String CheckName;
        private String ImageSeen;
        private String ImageResult;
        private String ReportTime;
        private String ApplyTime;
        private int PrintFlag;
        private String ReportURL;
        private Object Reportime;

        protected CheckItemListBean(Parcel in) {
            SerialNum = in.readInt();
            AdmissTimes = in.readInt();
            CheckPart = in.readString();
            CheckName = in.readString();
            ImageSeen = in.readString();
            ImageResult = in.readString();
            ReportTime = in.readString();
            ApplyTime = in.readString();
            PrintFlag = in.readInt();
            ReportURL = in.readString();
        }

        public static final Creator<CheckItemListBean> CREATOR = new Creator<CheckItemListBean>() {
            @Override
            public CheckItemListBean createFromParcel(Parcel in) {
                return new CheckItemListBean(in);
            }

            @Override
            public CheckItemListBean[] newArray(int size) {
                return new CheckItemListBean[size];
            }
        };

        public int getSerialNum() {
            return SerialNum;
        }

        public void setSerialNum(int SerialNum) {
            this.SerialNum = SerialNum;
        }

        public Object getPatientID() {
            return PatientID;
        }

        public void setPatientID(Object PatientID) {
            this.PatientID = PatientID;
        }

        public Object getInPatientNo() {
            return InPatientNo;
        }

        public void setInPatientNo(Object InPatientNo) {
            this.InPatientNo = InPatientNo;
        }

        public int getAdmissTimes() {
            return AdmissTimes;
        }

        public void setAdmissTimes(int AdmissTimes) {
            this.AdmissTimes = AdmissTimes;
        }

        public String getCheckPart() {
            return CheckPart;
        }

        public void setCheckPart(String CheckPart) {
            this.CheckPart = CheckPart;
        }

        public String getCheckName() {
            return CheckName;
        }

        public void setCheckName(String CheckName) {
            this.CheckName = CheckName;
        }

        public String getImageSeen() {
            return ImageSeen;
        }

        public void setImageSeen(String ImageSeen) {
            this.ImageSeen = ImageSeen;
        }

        public String getImageResult() {
            return ImageResult;
        }

        public void setImageResult(String ImageResult) {
            this.ImageResult = ImageResult;
        }

        public String getReportTime() {
            return ReportTime;
        }

        public void setReportTime(String ReportTime) {
            this.ReportTime = ReportTime;
        }

        public String getApplyTime() {
            return ApplyTime;
        }

        public void setApplyTime(String ApplyTime) {
            this.ApplyTime = ApplyTime;
        }

        public int getPrintFlag() {
            return PrintFlag;
        }

        public void setPrintFlag(int PrintFlag) {
            this.PrintFlag = PrintFlag;
        }

        public String getReportURL() {
            return ReportURL;
        }

        public void setReportURL(String ReportURL) {
            this.ReportURL = ReportURL;
        }

        public Object getReportime() {
            return Reportime;
        }

        public void setReportime(Object Reportime) {
            this.Reportime = Reportime;
        }

        @Override
        public String toString() {
            return "CheckItemListBean{" +
                    "SerialNum=" + SerialNum +
                    ", PatientID=" + PatientID +
                    ", InPatientNo=" + InPatientNo +
                    ", AdmissTimes=" + AdmissTimes +
                    ", CheckPart='" + CheckPart + '\'' +
                    ", CheckName='" + CheckName + '\'' +
                    ", ImageSeen='" + ImageSeen + '\'' +
                    ", ImageResult='" + ImageResult + '\'' +
                    ", ReportTime='" + ReportTime + '\'' +
                    ", ApplyTime='" + ApplyTime + '\'' +
                    ", PrintFlag=" + PrintFlag +
                    ", ReportURL='" + ReportURL + '\'' +
                    ", Reportime=" + Reportime +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(SerialNum);
            dest.writeInt(AdmissTimes);
            dest.writeString(CheckPart);
            dest.writeString(CheckName);
            dest.writeString(ImageSeen);
            dest.writeString(ImageResult);
            dest.writeString(ReportTime);
            dest.writeString(ApplyTime);
            dest.writeInt(PrintFlag);
            dest.writeString(ReportURL);
        }
    }

    @Override
    public String toString() {
        return "CheckBean{" +
                "sn=" + sn +
                ", repID='" + repID + '\'' +
                ", ApplyID='" + ApplyID + '\'' +
                ", ReportCode='" + ReportCode + '\'' +
                ", ReportName='" + ReportName + '\'' +
                ", TimeList=" + TimeList +
                ", BdChkRptDiag=" + BdChkRptDiag +
                ", BdCheckRptType=" + BdCheckRptType +
                ", Patient=" + Patient +
                ", ImgReportList=" + ImgReportList +
                ", RisTime='" + RisTime + '\'' +
                ", RptTime='" + RptTime + '\'' +
                ", CheckItemList=" + CheckItemList +
                '}';
    }
}
