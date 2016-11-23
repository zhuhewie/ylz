package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-9-21.
 * 医生预约排班的信息
 */
public class DoctorAppMessageBean implements Parcelable {

    /**
     * Organization : null
     * OrganizationName : null
     * DeptCode : A0301
     * DeptName : 妇科
     * DoctorCode : 7023
     * DoctorName : 钟兴秀
     * DoctorIntroduction : 钟兴秀
     * ScheduleId : 5166
     * QueueSn : null
     * QueueType : null
     * QueueTypeName : null
     * BeginTime : null
     * EndTime : null
     * InvalidTime : null
     * AdviceTime : null
     * ClinicResponceCode : 02
     * ClinicResponceName : 副主任医师
     * ShiftCode : 1
     * ShiftName : 上午
     * RoomNo : 22
     * RoomName : 门诊楼4楼3号
     * FlagName : 20
     * ClinicResponceFee : 6.0
     * RegisterFee : 1.0
     * Status : null
     * ScheduleDate : 2016-09-22 00:00:00
     * InspectingNum : 0
     * BkStatus : 0
     */

    public String Organization;         //机构编码
    public String OrganizationName;     //机构名称
    public String DeptCode;             //科室编码
    public String DeptName;             //科室名称
    public String DoctorCode;           //医生工号
    public String DoctorName;           //医生名称
    public String DoctorIntroduction;   //医生简介
    public String ScheduleId;           //排班号
    public String QueueSn;              //排队号
    public String QueueType;            //适用类型
    public String QueueTypeName;        //适用类型名称
    public String BeginTime;            //开始时间
    public String EndTime;              //结束时间
    public String InvalidTime;          //过期时间
    public String AdviceTime;           //建议就诊时间
    public String ClinicResponceCode;   //出诊身份id
    public String ClinicResponceName;   //出诊身份名字
    public String ShiftCode;            //班次编码
    public String ShiftName;            //班次名称
    public String RoomNo;               //
    public String RoomName;
    public String FlagName;
    public String ClinicResponceFee;    //出诊费用
    public String RegisterFee;      //挂号费
    /// <summary>
/// 预约状态
/// 0	可预约
/// 1	已预约
/// 2	已挂号
/// 3	已分诊
/// 4	不可预约
/// 5	已就诊
/// 6	失约
/// 7	取消就诊
/// 8   取消预约
/// </summary>
    public String Status;
    public String ScheduleDate;    //出诊日期
    public String InspectingNum;
    public String BkStatus;


    public DoctorAppMessageBean() {
    }

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String deptCode) {
        DeptCode = deptCode;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getDoctorCode() {
        return DoctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        DoctorCode = doctorCode;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctorIntroduction() {
        return DoctorIntroduction;
    }

    public void setDoctorIntroduction(String doctorIntroduction) {
        DoctorIntroduction = doctorIntroduction;
    }

    public String getScheduleId() {
        return ScheduleId;
    }

    public void setScheduleId(String scheduleId) {
        ScheduleId = scheduleId;
    }

    public String getQueueSn() {
        return QueueSn;
    }

    public void setQueueSn(String queueSn) {
        QueueSn = queueSn;
    }

    public String getQueueType() {
        return QueueType;
    }

    public void setQueueType(String queueType) {
        QueueType = queueType;
    }

    public String getQueueTypeName() {
        return QueueTypeName;
    }

    public void setQueueTypeName(String queueTypeName) {
        QueueTypeName = queueTypeName;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getInvalidTime() {
        return InvalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        InvalidTime = invalidTime;
    }

    public String getAdviceTime() {
        return AdviceTime;
    }

    public void setAdviceTime(String adviceTime) {
        AdviceTime = adviceTime;
    }

    public String getClinicResponceCode() {
        return ClinicResponceCode;
    }

    public void setClinicResponceCode(String clinicResponceCode) {
        ClinicResponceCode = clinicResponceCode;
    }

    public String getClinicResponceName() {
        return ClinicResponceName;
    }

    public void setClinicResponceName(String clinicResponceName) {
        ClinicResponceName = clinicResponceName;
    }

    public String getShiftCode() {
        return ShiftCode;
    }

    public void setShiftCode(String shiftCode) {
        ShiftCode = shiftCode;
    }

    public String getShiftName() {
        return ShiftName;
    }

    public void setShiftName(String shiftName) {
        ShiftName = shiftName;
    }

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public String getFlagName() {
        return FlagName;
    }

    public void setFlagName(String flagName) {
        FlagName = flagName;
    }

    public String getClinicResponceFee() {
        return ClinicResponceFee;
    }

    public void setClinicResponceFee(String clinicResponceFee) {
        ClinicResponceFee = clinicResponceFee;
    }

    public String getRegisterFee() {
        return RegisterFee;
    }

    public void setRegisterFee(String registerFee) {
        RegisterFee = registerFee;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getScheduleDate() {
        return ScheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        ScheduleDate = scheduleDate;
    }

    public String getInspectingNum() {
        return InspectingNum;
    }

    public void setInspectingNum(String inspectingNum) {
        InspectingNum = inspectingNum;
    }

    public String getBkStatus() {
        return BkStatus;
    }

    public void setBkStatus(String bkStatus) {
        BkStatus = bkStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString( Organization);
        parcel.writeString( OrganizationName);
        parcel.writeString(DeptCode);
        parcel.writeString(DeptName);
        parcel.writeString(DoctorCode);
        parcel.writeString(DoctorName);
        parcel.writeString(DoctorIntroduction);
        parcel.writeString(ScheduleId);
        parcel.writeString(QueueSn);
        parcel.writeString(QueueType);
        parcel.writeString(QueueTypeName);
        parcel.writeString(BeginTime);
        parcel.writeString(EndTime);
        parcel.writeString(InvalidTime);
        parcel.writeString(AdviceTime);
        parcel.writeString(ClinicResponceCode);
        parcel.writeString(ClinicResponceName);
        parcel.writeString(ShiftCode);
        parcel.writeString(ShiftName);
        parcel.writeString(RoomNo);
        parcel.writeString(RoomName);
        parcel.writeString(FlagName);
        parcel.writeString(ClinicResponceFee);
        parcel.writeString(RegisterFee);
        parcel.writeString(Status);
        parcel.writeString(ScheduleDate);
        parcel.writeString(InspectingNum);
        parcel.writeString(BkStatus);
    }

    public DoctorAppMessageBean (Parcel in) {
        Organization=in.readString();
        OrganizationName=in.readString();
        DeptCode=in.readString();
        DeptName=in.readString();
        DoctorCode=in.readString();
        DoctorName=in.readString();
        DoctorIntroduction=in.readString();
        ScheduleId=in.readString();
        QueueSn=in.readString();
        QueueType=in.readString();
        QueueTypeName=in.readString();
        BeginTime=in.readString();
        EndTime=in.readString();
        InvalidTime=in.readString();
        AdviceTime=in.readString();
        ClinicResponceCode=in.readString();
        ClinicResponceName=in.readString();
        ShiftCode=in.readString();
        ShiftName=in.readString();
        RoomNo=in.readString();
        RoomName=in.readString();
        FlagName=in.readString();
        ClinicResponceFee=in.readString();
        RegisterFee=in.readString();
        Status=in.readString();
        ScheduleDate=in.readString();
        InspectingNum=in.readString();
        BkStatus=in.readString();
    }

    public static final Creator<DoctorAppMessageBean> CREATOR = new Creator<DoctorAppMessageBean>() {
        @Override
        public DoctorAppMessageBean createFromParcel(Parcel parcel) {
            return new DoctorAppMessageBean(parcel);
        }

        @Override
        public DoctorAppMessageBean[] newArray(int i) {
            return new DoctorAppMessageBean[i];
        }
    };

    @Override
    public String toString() {
        return "DoctorAppMessageBean{" +
                "Organization='" + Organization + '\'' +
                ", OrganizationName='" + OrganizationName + '\'' +
                ", DeptCode='" + DeptCode + '\'' +
                ", DeptName='" + DeptName + '\'' +
                ", DoctorCode='" + DoctorCode + '\'' +
                ", DoctorName='" + DoctorName + '\'' +
                ", DoctorIntroduction='" + DoctorIntroduction + '\'' +
                ", ScheduleId='" + ScheduleId + '\'' +
                ", QueueSn='" + QueueSn + '\'' +
                ", QueueType='" + QueueType + '\'' +
                ", QueueTypeName='" + QueueTypeName + '\'' +
                ", BeginTime='" + BeginTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", InvalidTime='" + InvalidTime + '\'' +
                ", AdviceTime='" + AdviceTime + '\'' +
                ", ClinicResponceCode='" + ClinicResponceCode + '\'' +
                ", ClinicResponceName='" + ClinicResponceName + '\'' +
                ", ShiftCode='" + ShiftCode + '\'' +
                ", ShiftName='" + ShiftName + '\'' +
                ", RoomNo='" + RoomNo + '\'' +
                ", RoomName='" + RoomName + '\'' +
                ", FlagName='" + FlagName + '\'' +
                ", ClinicResponceFee='" + ClinicResponceFee + '\'' +
                ", RegisterFee='" + RegisterFee + '\'' +
                ", Status='" + Status + '\'' +
                ", ScheduleDate='" + ScheduleDate + '\'' +
                ", InspectingNum='" + InspectingNum + '\'' +
                ", BkStatus='" + BkStatus + '\'' +
                '}';
    }
}
