package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ylzpatient.hq.zz.com.yilianzhongpatient.GeneralConfig;
import ylzpatient.hq.zz.com.yilianzhongpatient.R;
import ylzpatient.hq.zz.com.yilianzhongpatient.bean.response.IndexBean;
import ylzpatient.hq.zz.com.yilianzhongpatient.utils.UtilApplication;

/**
 * Created by Administrator on 2016-10-8.
 * 服务界面的滚动图的adapter
 */
public class SerRollAdapter extends PagerAdapter {
    private List<ImageView> mCacheViews = new ArrayList<>();
    private List<IndexBean.AdsBean> listAds;

    public SerRollAdapter(List<IndexBean.AdsBean> listAds) {
        if (listAds == null || listAds.size() == 0) {
            this.listAds = new ArrayList<>();
            IndexBean.AdsBean as = new IndexBean.AdsBean();
            as.setPicture(R.mipmap.picture_2x + "");
            this.listAds.add(as);
        } else {
            this.listAds = listAds;
        }
    }

    public void setData(List<IndexBean.AdsBean> listAds) {
        if (listAds == null || listAds.size() == 0) {
            IndexBean.AdsBean as = new IndexBean.AdsBean();
            as.setPicture(R.mipmap.picture_2x + "");
            this.listAds.add(as);
        } else {
            this.listAds = listAds;
        }
    }

    /**
     * 销毁对象
     *
     * @param position 将要被销毁对象的索引位置
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mCacheViews.add((ImageView) object);
    }


    /**
     * 初始化一个对象
     * <p>
     * 将要被创建的对象的索引位置
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv;
        int index = position % listAds.size();
        if (mCacheViews.size() > 0) {
            iv = mCacheViews.remove(0);
        } else {
            iv = new ImageView(UtilApplication.getContextObject());

            iv.setLayoutParams(new ViewPager.LayoutParams());
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setId(position);
        }
        Picasso.with(UtilApplication.getContextObject())
                .load(listAds.get(index).getPicture()) //性别照片
                //.transform(new CircleTransform()) //圆
                .tag(SerRollAdapter.class.getName())
                .resize(GeneralConfig.screenWidth, (int) (GeneralConfig.screenWidth / (2.8))) //图片宽高
                .centerCrop()
                .into(iv);

        container.addView(iv);

        return iv;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    /**
     * 复用对象 true 复用对象 false 用的是object
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}


