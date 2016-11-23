package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-10-10.
 */
public class PreWaitImgAdapter extends PagerAdapter {
    private ArrayList<ImageView> viewList;
//    private ArrayList<String> mSelectPath;


    public PreWaitImgAdapter(ArrayList<ImageView> viewList) {
        this.viewList = viewList;
    }

    /**
     * 返回页卡的数量
     */
    @Override
    public int getCount() {
        int result = viewList == null?0:viewList.size();
        return result;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 销毁一个页卡
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 实例化一个页卡
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return  viewList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
