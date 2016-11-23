package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-9-1.
 * 可以左右滑动的viewpager的adapter
 */
public class ScollViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
//    String[]{"我的授权", "授权给我"}
    private List<String> mTitles = new ArrayList<>();

    public ScollViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList,List<String> mTitles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
