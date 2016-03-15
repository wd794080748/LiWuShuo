package com.wangdong.meilishuo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by wd794 on 2016/3/14 0014.
 */
public class ZhiNanViewPageAdapter extends FragmentStatePagerAdapter {
    private List<String> titleList;
    private List<Fragment> fragmentList;
    public ZhiNanViewPageAdapter(FragmentManager fm, List<String> titleList, List<Fragment> fragmentList) {
        super(fm);
        this.titleList=titleList;
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList==null?null:fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList==null?0:titleList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        Log.i("wangdong1", "getPageTitle: "+titleList.get(position));
        return titleList.get(position);
    }
}
