package com.gyngro.vanbora;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Alexandre Lara on 01/07/2016.
 */

public class GroupPagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public GroupPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                GroupTabFragment groupTab = new GroupTabFragment();
                return groupTab;
            case 1:
                MembersTabFragment groupTab2 = new MembersTabFragment();
                return groupTab2;
            case 2:
                InfoTabFragment groupTab3 = new InfoTabFragment();
                return groupTab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
