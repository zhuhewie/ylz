package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8.
 * 检验详情的返回实体
 */

public class InspectionDetailBean {

    private Object sn;
    private String repID;
    private Object ApplyID;
    private Object ReportName;
    private Object ReportCode;
    private Object LisTime;
    private Object RptTime;
    private List<DrugSensBean> DrugSensCheckItemList=new ArrayList<>();
    private List<SmearCheckBean> SmearCheckItemList=new ArrayList<>();
    private Object LisRptDiag;
    private int ReportTypeValue;
    private int ReportType;
    private Object ImgReportList;
    private Object TreatDeptCode;
    private Object TreatDeptName;
    /**
     * sn : 0
     * ApplyId : null
     * PatientID : null
     * InPatientNo : null
     * AdmissTimes : null
     * GroupCode : null
     * GroupName : 生化
     * ApplyTime : 2011-12-27 12:23:44
     * ItemEgName : BUN
     * ItemCnName : 尿素氮
     * ItemResult : 5.28
     * ItemUnit : mmol/L
     * ItemRange : 1.79--7.14
     * UpLowHint :
     * PrintFlag : 0
     * Lishistory : null
     * Reportime : null
     */

    private List<CheckItemListBean> CheckItemList=new ArrayList<>();

    public Object getSn() {
        return sn;
    }

    public void setSn(Object sn) {
        this.sn = sn;
    }

    public String getRepID() {
        return repID;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public Object getApplyID() {
        return ApplyID;
    }

    public void setApplyID(Object ApplyID) {
        this.ApplyID = ApplyID;
    }

    public Object getReportName() {
        return ReportName;
    }

    public void setReportName(Object ReportName) {
        this.ReportName = ReportName;
    }

    public Object getReportCode() {
        return ReportCode;
    }

    public void setReportCode(Object ReportCode) {
        this.ReportCode = ReportCode;
    }

    public Object getLisTime() {
        return LisTime;
    }

    public void setLisTime(Object LisTime) {
        this.LisTime = LisTime;
    }

    public Object getRptTime() {
        return RptTime;
    }

    public void setRptTime(Object RptTime) {
        this.RptTime = RptTime;
    }

    public List<DrugSensBean> getDrugSensCheckItemList() {
        return DrugSensCheckItemList;
    }

    public void setDrugSensCheckItemList(List<DrugSensBean> drugSensCheckItemList) {
        DrugSensCheckItemList = drugSensCheckItemList;
    }

    public List<SmearCheckBean> getSmearCheckItemList() {
        return SmearCheckItemList;
    }

    public void setSmearCheckItemList(List<SmearCheckBean> smearCheckItemList) {
        SmearCheckItemList = smearCheckItemList;
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

    public List<CheckItemListBean> getCheckItemList() {
        return CheckItemList;
    }

    public void setCheckItemList(List<CheckItemListBean> CheckItemList) {
        this.CheckItemList = CheckItemList;
    }

    public static class SmearCheckBean {

        /**
         * ItemResult :  沙眼衣原体抗原检测（胶体金法）：阴性
         * PositiveFlag : 阴
         */

        private String ItemResult;
        private String PositiveFlag;

        public String getItemResult() {
            return ItemResult;
        }

        public void setItemResult(String ItemResult) {
            this.ItemResult = ItemResult;
        }

        public String getPositiveFlag() {
            return PositiveFlag;
        }

        public void setPositiveFlag(String PositiveFlag) {
            this.PositiveFlag = PositiveFlag;
        }

        @Override
        public String toString() {
            return "SmearCheckBean{" +
                    "ItemResult='" + ItemResult + '\'' +
                    ", PositiveFlag='" + PositiveFlag + '\'' +
                    '}';
        }
    }

    public static class DrugSensBean {

        /**
         * BacteriaName : 解脲脲原体
         * BacteriaEName : Uu
         * BacteriaNumber :
         * DrugSensResList : [{"AntimicrobicEgName":"Azithromycin","AntimicrobicCnName":"阿奇霉素","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"Erythromycin","AntimicrobicCnName":"红霉素","MICResult":"","Sensitivity":"中介","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"GAT","AntimicrobicCnName":"加替沙星","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"Thiamphenicol","AntimicrobicCnName":"甲砜霉素","MICResult":"","Sensitivity":"中介","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"JOS","AntimicrobicCnName":"交沙霉素","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"CLR","AntimicrobicCnName":"克拉霉素","MICResult":"","Sensitivity":"耐药","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"Clindamycin","AntimicrobicCnName":"克林霉素","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"Roxithromicin","AntimicrobicCnName":"罗红霉素","MICResult":"","Sensitivity":"中介","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"MIN","AntimicrobicCnName":"美满霉素","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"D","AntimicrobicCnName":"强力霉素","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"SPLX","AntimicrobicCnName":"司帕沙星","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""},{"AntimicrobicEgName":"Levoflcxacin","AntimicrobicCnName":"左氧氟沙星","MICResult":"","Sensitivity":"*  敏感","BacteriaName":"解脲脲原体","BacteriaEName":"Uu","BacteriaNumber":"","Evaluation":""}]
         * Evaluation :
         */

        private String BacteriaName;
        private String BacteriaEName;
        private String BacteriaNumber;
        private String Evaluation;
        /**
         * AntimicrobicEgName : Azithromycin
         * AntimicrobicCnName : 阿奇霉素
         * MICResult :
         * Sensitivity : *  敏感
         * BacteriaName : 解脲脲原体
         * BacteriaEName : Uu
         * BacteriaNumber :
         * Evaluation :
         */

        private List<DrugSensResListBean> DrugSensResList;

        public String getBacteriaName() {
            return BacteriaName;
        }

        public void setBacteriaName(String BacteriaName) {
            this.BacteriaName = BacteriaName;
        }

        public String getBacteriaEName() {
            return BacteriaEName;
        }

        public void setBacteriaEName(String BacteriaEName) {
            this.BacteriaEName = BacteriaEName;
        }

        public String getBacteriaNumber() {
            return BacteriaNumber;
        }

        public void setBacteriaNumber(String BacteriaNumber) {
            this.BacteriaNumber = BacteriaNumber;
        }

        public String getEvaluation() {
            return Evaluation;
        }

        public void setEvaluation(String Evaluation) {
            this.Evaluation = Evaluation;
        }

        public List<DrugSensResListBean> getDrugSensResList() {
            return DrugSensResList;
        }

        public void setDrugSensResList(List<DrugSensResListBean> DrugSensResList) {
            this.DrugSensResList = DrugSensResList;
        }

        public static class DrugSensResListBean {
            private String AntimicrobicEgName;
            private String AntimicrobicCnName;
            private String MICResult;
            private String Sensitivity;
            private String BacteriaName;
            private String BacteriaEName;
            private String BacteriaNumber;
            private String Evaluation;

            public String getAntimicrobicEgName() {
                return AntimicrobicEgName;
            }

            public void setAntimicrobicEgName(String AntimicrobicEgName) {
                this.AntimicrobicEgName = AntimicrobicEgName;
            }

            public String getAntimicrobicCnName() {
                return AntimicrobicCnName;
            }

            public void setAntimicrobicCnName(String AntimicrobicCnName) {
                this.AntimicrobicCnName = AntimicrobicCnName;
            }

            public String getMICResult() {
                return MICResult;
            }

            public void setMICResult(String MICResult) {
                this.MICResult = MICResult;
            }

            public String getSensitivity() {
                return Sensitivity;
            }

            public void setSensitivity(String Sensitivity) {
                this.Sensitivity = Sensitivity;
            }

            public String getBacteriaName() {
                return BacteriaName;
            }

            public void setBacteriaName(String BacteriaName) {
                this.BacteriaName = BacteriaName;
            }

            public String getBacteriaEName() {
                return BacteriaEName;
            }

            public void setBacteriaEName(String BacteriaEName) {
                this.BacteriaEName = BacteriaEName;
            }

            public String getBacteriaNumber() {
                return BacteriaNumber;
            }

            public void setBacteriaNumber(String BacteriaNumber) {
                this.BacteriaNumber = BacteriaNumber;
            }

            public String getEvaluation() {
                return Evaluation;
            }

            public void setEvaluation(String Evaluation) {
                this.Evaluation = Evaluation;
            }

            @Override
            public String toString() {
                return "DrugSensResListBean{" +
                        "AntimicrobicEgName='" + AntimicrobicEgName + '\'' +
                        ", AntimicrobicCnName='" + AntimicrobicCnName + '\'' +
                        ", MICResult='" + MICResult + '\'' +
                        ", Sensitivity='" + Sensitivity + '\'' +
                        ", BacteriaName='" + BacteriaName + '\'' +
                        ", BacteriaEName='" + BacteriaEName + '\'' +
                        ", BacteriaNumber='" + BacteriaNumber + '\'' +
                        ", Evaluation='" + Evaluation + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DrugSensBean{" +
                    "BacteriaName='" + BacteriaName + '\'' +
                    ", BacteriaEName='" + BacteriaEName + '\'' +
                    ", BacteriaNumber='" + BacteriaNumber + '\'' +
                    ", Evaluation='" + Evaluation + '\'' +
                    ", DrugSensResList=" + DrugSensResList +
                    '}';
        }
    }

    public static class CheckItemListBean {
        private int sn;
        private Object ApplyId;
        private Object PatientID;
        private Object InPatientNo;
        private int AdmissTimes;
        private Object GroupCode;
        private String GroupName;
        private String ApplyTime;
        private String ItemEgName;
        private String ItemCnName;
        private String ItemResult;
        private String ItemUnit;
        private String ItemRange;
        private String UpLowHint;
        private int PrintFlag;
        private Object Lishistory;
        private Object Reportime;

        public int getSn() {
            return sn;
        }

        public void setSn(int sn) {
            this.sn = sn;
        }

        public Object getApplyId() {
            return ApplyId;
        }

        public void setApplyId(Object ApplyId) {
            this.ApplyId = ApplyId;
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

        public void setAdmissTimes(int admissTimes) {
            AdmissTimes = admissTimes;
        }

        public Object getGroupCode() {
            return GroupCode;
        }

        public void setGroupCode(Object GroupCode) {
            this.GroupCode = GroupCode;
        }

        public String getGroupName() {
            return GroupName;
        }

        public void setGroupName(String GroupName) {
            this.GroupName = GroupName;
        }

        public String getApplyTime() {
            return ApplyTime;
        }

        public void setApplyTime(String ApplyTime) {
            this.ApplyTime = ApplyTime;
        }

        public String getItemEgName() {
            return ItemEgName;
        }

        public void setItemEgName(String ItemEgName) {
            this.ItemEgName = ItemEgName;
        }

        public String getItemCnName() {
            return ItemCnName;
        }

        public void setItemCnName(String ItemCnName) {
            this.ItemCnName = ItemCnName;
        }

        public String getItemResult() {
            return ItemResult;
        }

        public void setItemResult(String ItemResult) {
            this.ItemResult = ItemResult;
        }

        public String getItemUnit() {
            return ItemUnit;
        }

        public void setItemUnit(String ItemUnit) {
            this.ItemUnit = ItemUnit;
        }

        public String getItemRange() {
            return ItemRange;
        }

        public void setItemRange(String ItemRange) {
            this.ItemRange = ItemRange;
        }

        public String getUpLowHint() {
            return UpLowHint;
        }

        public void setUpLowHint(String UpLowHint) {
            this.UpLowHint = UpLowHint;
        }

        public int getPrintFlag() {
            return PrintFlag;
        }

        public void setPrintFlag(int PrintFlag) {
            this.PrintFlag = PrintFlag;
        }

        public Object getLishistory() {
            return Lishistory;
        }

        public void setLishistory(Object Lishistory) {
            this.Lishistory = Lishistory;
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
                    "sn=" + sn +
                    ", ApplyId=" + ApplyId +
                    ", PatientID=" + PatientID +
                    ", InPatientNo=" + InPatientNo +
                    ", AdmissTimes=" + AdmissTimes +
                    ", GroupCode=" + GroupCode +
                    ", GroupName='" + GroupName + '\'' +
                    ", ApplyTime='" + ApplyTime + '\'' +
                    ", ItemEgName='" + ItemEgName + '\'' +
                    ", ItemCnName='" + ItemCnName + '\'' +
                    ", ItemResult='" + ItemResult + '\'' +
                    ", ItemUnit='" + ItemUnit + '\'' +
                    ", ItemRange='" + ItemRange + '\'' +
                    ", UpLowHint='" + UpLowHint + '\'' +
                    ", PrintFlag=" + PrintFlag +
                    ", Lishistory=" + Lishistory +
                    ", Reportime=" + Reportime +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "InspectionDetail{" +
                "sn=" + sn +
                ", repID='" + repID + '\'' +
                ", ApplyID=" + ApplyID +
                ", ReportName=" + ReportName +
                ", ReportCode=" + ReportCode +
                ", LisTime=" + LisTime +
                ", RptTime=" + RptTime +
                ", DrugSensCheckItemList=" + DrugSensCheckItemList +
                ", SmearCheckItemList=" + SmearCheckItemList +
                ", LisRptDiag=" + LisRptDiag +
                ", ReportTypeValue=" + ReportTypeValue +
                ", ReportType=" + ReportType +
                ", ImgReportList=" + ImgReportList +
                ", TreatDeptCode=" + TreatDeptCode +
                ", TreatDeptName=" + TreatDeptName +
                ", CheckItemList=" + CheckItemList +
                '}';
    }}
