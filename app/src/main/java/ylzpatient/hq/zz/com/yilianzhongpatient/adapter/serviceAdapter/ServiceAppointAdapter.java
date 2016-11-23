package ylzpatient.hq.zz.com.yilianzhongpatient.adapter.serviceAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016-9-1.
 * 门诊预约界面的左右滑动的FragmentPagerAdapter
 */
public class ServiceAppointAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private String[] mTitles = new String[]{"按医院预约", "按疾病预约"};
    public ServiceAppointAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
