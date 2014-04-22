package org.pasut.prode.activities;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.pasut.prode.R;
import org.pasut.prode.fragments.CurrentFragment;
import org.pasut.prode.fragments.MyCardsFragment;
import org.pasut.prode.fragments.RankingFragment;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_main)
public class MainActivity extends RoboFragmentActivity implements ActionBar.TabListener {
    private MainPagerAdapter pageAdapter;

    private int[] tabTitles = new int[] {R.string.current_matches, R.string.my_cards, R.string.ranking};

    @InjectView(R.id.pager)
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageAdapter = new MainPagerAdapter(getSupportFragmentManager());
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent_black)));
        }
        pager.setAdapter(pageAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });
        actionBar.addTab(actionBar.newTab().setText(pageAdapter.getPageTitle(0)).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(pageAdapter.getPageTitle(1)).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(pageAdapter.getPageTitle(2)).setTabListener(this));
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {}

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {}

    private class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    return MyCardsFragment.newInstance();
                case 2:
                    return RankingFragment.newInstance();
                default:
                    return CurrentFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(tabTitles[position]);
        }
    }
}
