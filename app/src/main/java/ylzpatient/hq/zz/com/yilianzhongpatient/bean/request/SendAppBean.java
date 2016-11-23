package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-9-29.
 */
public class SendAppBean {


    /**
     * 提交预预约申请的bean
     * BookingType : 99
     * OutPat : {"Mobile":"15112840868","PatientID":"0001400","PatientName":"zhuhww"}
     * ScheduleDetail : {"BeginTime":"16:00","EndTime":"17:00","ScheduleId":"5289"}
     */

    private String BookingType;
    /**
     * Mobile : 15112840868
     * PatientID : 0001400
     * PatientName : zhuhww
     */

    private OutPatBean OutPat;
    /**
     * BeginTime : 16:00
     * EndTime : 17:00
     * ScheduleId : 5289
     */

    private ScheduleDetailBean ScheduleDetail;

    public SendAppBean() {
        OutPat = new OutPatBean();
        ScheduleDetail = new ScheduleDetailBean();
    }

    public SendAppBean(String bookingType, OutPatBean outPat, ScheduleDetailBean scheduleDetail) {
        BookingType = bookingType;
        OutPat = outPat;
        ScheduleDetail = scheduleDetail;
    }

    public String getBookingType() {
        return BookingType;
    }

    public void setBookingType(String BookingType) {
        this.BookingType = BookingType;
    }

    public OutPatBean getOutPat() {
        return OutPat;
    }

    public void setOutPat(OutPatBean OutPat) {
        this.OutPat = OutPat;
    }

    public ScheduleDetailBean getScheduleDetail() {
        return ScheduleDetail;
    }

    public void setScheduleDetail(ScheduleDetailBean ScheduleDetail) {
        this.ScheduleDetail = ScheduleDetail;
    }

    public static class OutPatBean {
        private String Mobile;
        private String PatientID;
        private String PatientName;

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getPatientID() {
            return PatientID;
        }

        public void setPatientID(String PatientID) {
            this.PatientID = PatientID;
        }

        public String getPatientName() {
            return PatientName;
        }

        public void setPatientName(String PatientName) {
            this.PatientName = PatientName;
        }

        @Override
        public String toString() {
            return "OutPatBean{" +
                    "Mobile='" + Mobile + '\'' +
                    ", PatientID='" + PatientID + '\'' +
                    ", PatientName='" + PatientName + '\'' +
                    '}';
        }
    }

    public static class ScheduleDetailBean {
        private String BeginTime;
        private String EndTime;
        private String ScheduleId;

        public String getBeginTime() {
            return BeginTime;
        }

        public void setBeginTime(String BeginTime) {
            this.BeginTime = BeginTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getScheduleId() {
            return ScheduleId;
        }

        public void setScheduleId(String ScheduleId) {
            this.ScheduleId = ScheduleId;
        }

        @Override
        public String toString() {
            return "ScheduleDetailBean{" +
                    "BeginTime='" + BeginTime + '\'' +
                    ", EndTime='" + EndTime + '\'' +
                    ", ScheduleId='" + ScheduleId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SendAppBean{" +
                "BookingType='" + BookingType + '\'' +
                ", OutPat=" + OutPat +
                ", ScheduleDetail=" + ScheduleDetail +
                '}';
    }
}
