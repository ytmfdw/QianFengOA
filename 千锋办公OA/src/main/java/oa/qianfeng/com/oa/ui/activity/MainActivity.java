package oa.qianfeng.com.oa.ui.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.ui.fragment.AttendanceFragment;
import oa.qianfeng.com.oa.ui.fragment.BaseNetFragment;
import oa.qianfeng.com.oa.ui.fragment.MessageFragment;
import oa.qianfeng.com.oa.ui.fragment.MineFragment;

public class MainActivity extends BaseNetActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

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

    LayoutInflater inflater;


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

        viewpager.addOnPageChangeListener(this);

    }

    private void setupTabHost() {
        tabhost.setup(this, getSupportFragmentManager(), R.id.viewpager);
        inflater = LayoutInflater.from(this);

        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec tab = tabhost.newTabSpec("" + i);
            tab.setIndicator(getTahView(i));
            tabhost.addTab(tab, tabhostClasses[i], null);
            tabhost.getTabWidget().setDividerDrawable(new ColorDrawable(0x00000000));
        }

        tabhost.setOnTabChangedListener(this);
    }

    private View getTahView(int i) {

        View view = inflater.inflate(R.layout.tabhost_item, null);
        //创建TabView
        ImageView iv = (ImageView) view.findViewById(R.id.iv_tabimg);

        TextView tv = (TextView) view.findViewById(R.id.tv_tabtext);
        tv.setText(titles[i]);
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        int index = Integer.valueOf(tabId);
        viewpager.setCurrentItem(index);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabhost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
