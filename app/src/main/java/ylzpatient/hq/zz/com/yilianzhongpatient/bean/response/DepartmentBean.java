package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016-9-14.
 */
public class DepartmentBean implements Parcelable{

    /**
     * DeptNo : null
     * MzDeptCode : 7
     * MzDeptName : 普通内科
     * PyCode : null
     * WbCode : null
     * Address : null
     * ParentMzDeptCode : null
     * RoomFlag : null
     * RoomFlagName : 否
     * childDept : [{"DeptNo":null,"MzDeptCode":null,"MzDeptName":null,"PyCode":null,"WbCode":null,"Address":null,"ParentMzDeptCode":null,"RoomFlag":null,"RoomFlagName":"否","childDept":null,"Doctor":[{"Sn":0,"DoctorCode":"1084","DoctorName":"郑瑞红","PyCode":null,"WbCode":null,"Advantage":null,"JobTitleCode":null,"JobTitleName":null,"Introduction":null,"DeptCode":"7","DeptName":"普通内科","SecondDeptCode":null,"ClinicResponceName":"普通医师","Photo":null,"ScheduleList":null,"ScheduleExists":true}]}]
     * Doctor : [{"Sn":0,"DoctorCode":"1084","DoctorName":"郑瑞红","PyCode":null,"WbCode":null,"Advantage":null,"JobTitleCode":null,"JobTitleName":null,"Introduction":null,"DeptCode":"7","DeptName":"普通内科","SecondDeptCode":null,"ClinicResponceName":"普通医师","Photo":null,"ScheduleList":null,"ScheduleExists":true}]
     private Object DeptNo;
     private Object MzDeptCode;
     private Object MzDeptName;
     private Object PyCode;
     private Object WbCode;
     private Object Address;
     private Object ParentMzDeptCode;
     private Object RoomFlag;
     private String RoomFlagName;
     private Object childDept;
     */

    private Object DeptNo;
    private String MzDeptCode;
    private String MzDeptName;
    private Object PyCode;
    private Object WbCode;
    private Object Address;
    private Object ParentMzDeptCode;
    private Object RoomFlag;
    private String RoomFlagName;

    private List<DepartmentBean> childDept;

    private List<DoctorBean> Doctor;

    public Object getDeptNo() {
        return DeptNo;
    }

    public void setDeptNo(Object DeptNo) {
        this.DeptNo = DeptNo;
    }

    public String getMzDeptCode() {
        return MzDeptCode;
    }

    public void setMzDeptCode(String MzDeptCode) {
        this.MzDeptCode = MzDeptCode;
    }

    public String getMzDeptName() {
        return MzDeptName;
    }

    public void setMzDeptName(String MzDeptName) {
        this.MzDeptName = MzDeptName;
    }

    public Object getPyCode() {
        return PyCode;
    }

    public void setPyCode(Object PyCode) {
        this.PyCode = PyCode;
    }

    public Object getWbCode() {
        return WbCode;
    }

    public void setWbCode(Object WbCode) {
        this.WbCode = WbCode;
    }

    public Object getAddress() {
        return Address;
    }

    public void setAddress(Object Address) {
        this.Address = Address;
    }

    public Object getParentMzDeptCode() {
        return ParentMzDeptCode;
    }

    public void setParentMzDeptCode(Object ParentMzDeptCode) {
        this.ParentMzDeptCode = ParentMzDeptCode;
    }

    public Object getRoomFlag() {
        return RoomFlag;
    }

    public void setRoomFlag(Object RoomFlag) {
        this.RoomFlag = RoomFlag;
    }

    public String getRoomFlagName() {
        return RoomFlagName;
    }

    public void setRoomFlagName(String RoomFlagName) {
        this.RoomFlagName = RoomFlagName;
    }

    public List<DepartmentBean> getChildDept() {
        return childDept;
    }

    public void setChildDept(List<DepartmentBean> childDept) {
        this.childDept = childDept;
    }

    public List<DoctorBean> getDoctor() {
        return Doctor;
    }

    public void setDoctor(List<DoctorBean> Doctor) {
        this.Doctor = Doctor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(DeptNo);
        parcel.writeString(MzDeptCode);
        parcel.writeString(MzDeptName);
        parcel.writeValue(PyCode);
        parcel.writeValue(WbCode);
        parcel.writeValue(Address);
        parcel.writeValue(ParentMzDeptCode);
        parcel.writeValue(RoomFlag);
        parcel.writeString(RoomFlagName);
        parcel.writeList(childDept);
        parcel.writeList(Doctor);
    }

    public DepartmentBean(Parcel in) {
        DeptNo = in.readString();
        MzDeptCode = in.readString();
        MzDeptName = in.readString();
        PyCode = in.readString();
        WbCode = in.readString();
        Address = in.readString();
        ParentMzDeptCode = in.readString();
        RoomFlag = in.readString();
        RoomFlagName = in.readString();

        childDept = in.readArrayList(DepartmentBean.class.getClassLoader());
        Doctor = in.readArrayList(DoctorBean.class.getClassLoader());
    }

    public static final Creator<DepartmentBean> CREATOR = new Creator<DepartmentBean>() {
        @Override
        public DepartmentBean createFromParcel(Parcel in) {
            return new DepartmentBean(in);
        }

        @Override
        public DepartmentBean[] newArray(int size) {
            return new DepartmentBean[size];
        }
    };

    @Override
    public String toString() {
        return "DepartmentBean{" +
                "DeptNo=" + DeptNo +
                ", MzDeptCode='" + MzDeptCode + '\'' +
                ", MzDeptName='" + MzDeptName + '\'' +
                ", PyCode=" + PyCode +
                ", WbCode=" + WbCode +
                ", Address=" + Address +
                ", ParentMzDeptCode=" + ParentMzDeptCode +
                ", RoomFlag=" + RoomFlag +
                ", RoomFlagName='" + RoomFlagName + '\'' +
                ", childDept=" + childDept +
                ", Doctor=" + Doctor +
                '}';
    }
}
