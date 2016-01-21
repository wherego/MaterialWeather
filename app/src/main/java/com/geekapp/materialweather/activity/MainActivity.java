package com.geekapp.materialweather.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.geekapp.materialweather.R;
import com.geekapp.materialweather.fragment.DepthPageTransformer;
import com.geekapp.materialweather.fragment.TabFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static MainActivity activity;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    /* @Bind(R.id.toolbar)
     Toolbar mToolbar;*/
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity = this;
        initUIViews();
    }

    public void initUIViews() {

        boolean isFitsSystemWindows = ViewCompat.getFitsSystemWindows(mDrawerLayout);
        if (isFitsSystemWindows && Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mViewPager.setAdapter(new ScreenSideAdapter(getSupportFragmentManager()));
        //add animation to viewpager
        mViewPager.setPageTransformer(true, new DepthPageTransformer());

        //set drawer item listener
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public void setToolbarToDrawerLayout(Toolbar mToolbar) {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.navigation_item_1:
                intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.navigation_item_2:
                break;
        }
        return true;
    }

    private class ScreenSideAdapter extends FragmentStatePagerAdapter {

        public ScreenSideAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            return new TabFragment();
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return 2;
        }
    }

}
