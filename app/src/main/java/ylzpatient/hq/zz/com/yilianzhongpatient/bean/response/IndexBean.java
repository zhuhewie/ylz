package ylzpatient.hq.zz.com.yilianzhongpatient.bean.response;

import java.util.List;

/**
 * Created by Administrator on 2016-10-21.
 */

public class IndexBean {

    /**
     * AdId : 130016
     * Url :
     * Picture : http://192.168.1.128:8012/Upload/Files/HQ_8001/qq截图20161025102310.png
     */

    private List<AdsBean> Ads;
    /**
     * HospitalId : HQ_1000
     * Name : 广州惠侨
     * Logo :
     * ServerUrl : http://192.168.1.211:8006/wm.aspx/
     */

    private List<HospitalsBean> Hospitals;
    /**
     * Sn : 14
     * Logo : http://192.168.1.29:8088/
     * Name : 夕阳红套餐
     * StandardPrice : 0
     * DiscountPrice : 0
     */

    private List<MedicalPackagesBean> MedicalPackages;
    /**
     * NewsId : 8
     * Logo :
     * Title : 8测试2
     * Content : 测试
     */

    private List<NewsBean> News;

    public List<AdsBean> getAds() {
        return Ads;
    }

    public void setAds(List<AdsBean> Ads) {
        this.Ads = Ads;
    }

    public List<HospitalsBean> getHospitals() {
        return Hospitals;
    }

    public void setHospitals(List<HospitalsBean> Hospitals) {
        this.Hospitals = Hospitals;
    }

    public List<MedicalPackagesBean> getMedicalPackages() {
        return MedicalPackages;
    }

    public void setMedicalPackages(List<MedicalPackagesBean> MedicalPackages) {
        this.MedicalPackages = MedicalPackages;
    }

    public List<NewsBean> getNews() {
        return News;
    }

    public void setNews(List<NewsBean> News) {
        this.News = News;
    }

    public static class AdsBean {
        private int AdId;
        private String Url;
        private String Picture;

        public int getAdId() {
            return AdId;
        }

        public void setAdId(int AdId) {
            this.AdId = AdId;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getPicture() {
            return Picture;
        }

        public void setPicture(String Picture) {
            this.Picture = Picture;
        }

        @Override
        public String toString() {
            return "AdsBean{" +
                    "AdId=" + AdId +
                    ", Url='" + Url + '\'' +
                    ", Picture='" + Picture + '\'' +
                    '}';
        }
    }

    public static class HospitalsBean {
        private String HospitalId;
        private String Name;
        private String Logo;
        private String ServerUrl;

        public String getHospitalId() {
            return HospitalId;
        }

        public void setHospitalId(String HospitalId) {
            this.HospitalId = HospitalId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getServerUrl() {
            return ServerUrl;
        }

        public void setServerUrl(String ServerUrl) {
            this.ServerUrl = ServerUrl;
        }

        @Override
        public String toString() {
            return "HospitalsBean{" +
                    "HospitalId='" + HospitalId + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Logo='" + Logo + '\'' +
                    ", ServerUrl='" + ServerUrl + '\'' +
                    '}';
        }
    }

    public static class MedicalPackagesBean {
        private String Sn;
        private String Logo;
        private String Name;
        private int StandardPrice;
        private int DiscountPrice;

        public String getSn() {
            return Sn;
        }

        public void setSn(String Sn) {
            this.Sn = Sn;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getStandardPrice() {
            return StandardPrice;
        }

        public void setStandardPrice(int StandardPrice) {
            this.StandardPrice = StandardPrice;
        }

        public int getDiscountPrice() {
            return DiscountPrice;
        }

        public void setDiscountPrice(int DiscountPrice) {
            this.DiscountPrice = DiscountPrice;
        }

        @Override
        public String toString() {
            return "MedicalPackagesBean{" +
                    "Sn='" + Sn + '\'' +
                    ", Logo='" + Logo + '\'' +
                    ", Name='" + Name + '\'' +
                    ", StandardPrice=" + StandardPrice +
                    ", DiscountPrice=" + DiscountPrice +
                    '}';
        }
    }

    public static class NewsBean {
        private int NewsId;
        private String Logo;
        private String Title;
        private String Content;

        public int getNewsId() {
            return NewsId;
        }

        public void setNewsId(int NewsId) {
            this.NewsId = NewsId;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        @Override
        public String toString() {
            return "NewsBean{" +
                    "NewsId=" + NewsId +
                    ", Logo='" + Logo + '\'' +
                    ", Title='" + Title + '\'' +
                    ", Content='" + Content + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "IndexBean{" +
                "Ads=" + Ads +
                ", Hospitals=" + Hospitals +
                ", MedicalPackages=" + MedicalPackages +
                ", News=" + News +
                '}';
    }
}
