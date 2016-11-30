package oa.qianfeng.com.oa.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.ui.fragment.AttendanceFragment;
import oa.qianfeng.com.oa.ui.fragment.BaseNetFragment;
import oa.qianfeng.com.oa.ui.fragment.MessageFragment;
import oa.qianfeng.com.oa.ui.fragment.MineFragment;

public class MainActivity extends BaseNetActivity {

    String[] titles = {
            "我的", "考勤", "公告"
    };

    Class[] tabhostClasses = {
            MineFragment.class, AttendanceFragment.class, MessageFragment.class
    };

    BaseNetFragment[] fragments = new BaseNetFragment[tabhostClasses.length];
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabhost)
    FragmentTabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViewPager();
        setupTabHost();
    }

    private void initViewPager() {
        fragments[0] = new MineFragment();
        fragments[1] = new AttendanceFragment();
        fragments[2] = new MessageFragment();

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        };
        viewpager.setAdapter(adapter);

    }

    private void setupTabHost() {
        tabhost.setup(this, getSupportFragmentManager(), R.id.viewpager);
        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec tab = tabhost.newTabSpec("" + i);
            tab.setIndicator(getTahView(i));
            tabhost.addTab(tab, tabhostClasses[i], null);
            tabhost.getTabWidget().setDividerDrawable(new ColorDrawable(0x00000000));
        }
    }

    private View getTahView(int i) {
        //创建TabView
        TextView tv = new TextView(this);
        tv.setText(titles[i]);
        return tv;
    }
}
