package ylzpatient.hq.zz.com.yilianzhongpatient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016-8-24.
 * 主界面的实现底部菜单左右滑动的Adapter
 *  现在底部修改成了bottomNavitionbar
 *  暂时不用了
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MainFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
