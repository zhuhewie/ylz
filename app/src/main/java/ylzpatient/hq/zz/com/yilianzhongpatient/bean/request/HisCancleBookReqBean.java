package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-1.
 */

public class HisCancleBookReqBean {

    /**
     * QueueSn : 1
     * ScheduleId : 5408
     */

    public ScheduleDetailBean ScheduleDetail;
    /**
     * ScheduleDetail : {"QueueSn":1,"ScheduleId":"5408"}
     * BookingType : 99
     */

    private String BookingType;

    public ScheduleDetailBean getScheduleDetail() {
        return ScheduleDetail;
    }

    public void setScheduleDetail(ScheduleDetailBean ScheduleDetail) {
        this.ScheduleDetail = ScheduleDetail;
    }

    public String getBookingType() {
        return BookingType;
    }

    public void setBookingType(String BookingType) {
        this.BookingType = BookingType;
    }

    public static class ScheduleDetailBean {
        private String QueueSn;
        private String ScheduleId;

        public String getQueueSn() {
            return QueueSn;
        }

        public void setQueueSn(String QueueSn) {
            this.QueueSn = QueueSn;
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
                    "QueueSn='" + QueueSn + '\'' +
                    ", ScheduleId='" + ScheduleId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HisCancleBookReqBean{" +
                "ScheduleDetail=" + ScheduleDetail +
                ", BookingType='" + BookingType + '\'' +
                '}';
    }
}
