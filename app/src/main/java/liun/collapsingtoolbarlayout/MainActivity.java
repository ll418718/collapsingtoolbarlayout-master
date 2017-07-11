package liun.collapsingtoolbarlayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import krelve.view.Kanner;

public class MainActivity extends AppCompatActivity {
    Kanner kanner;
    TabLayout tabLayout;
    ViewPager viewPager;
    private List<Fragment> fragments;

    /**
     * app:layout_scrollFlags属性里面必须至少启用scroll这个flag，这样这个view才会滚动出屏幕，否则它将一直固定在顶部。可以使用的其他flag有：
     * enterAlways: 一旦向上滚动这个view就可见。
     * enterAlwaysCollapsed: 顾名思义，这个flag定义的是何时进入（已经消失之后何时再次显示）。假设你定义了一个最小高度（minHeight）同时enterAlways也定义了，那么view将在到达这个最小高度的时候开始显示，并且从这个时候开始慢慢展开，当滚动到顶部的时候展开完。
     * exitUntilCollapsed: 同样顾名思义，这个flag时定义何时退出，当你定义了一个minHeight，这个view将在滚动到达这个最小高度的时候消失。
     * 记住，要把带有scroll flag的view放在前面，这样收回的view才能让正常退出，而固定的view继续留在顶部
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rec);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        kanner = (Kanner) findViewById(R.id.show_event_detail_bg);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        int[] imagesRes = {R.mipmap.a, R.mipmap.b, R.mipmap.c,
                R.mipmap.d, R.mipmap.e};
        kanner.setImagesRes(imagesRes);
        initTab();
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
       // recyclerView.setAdapter(new MyRecyclerViewAdapter());
    }
    /*
      * 初始化tab
     */
    private void initTab() {
        fragments = getFragments();
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments,getNames());

        viewPager.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ItemFragment());
        fragments.add(new ItemFragment());
        fragments.add(new ItemFragment());
        return fragments;
    }

    /**
     * 假数据
     *
     * @return
     */
    public String[] getNames() {
        String[] mNames = new String[]{"热门", "附近", "关注"};
        for (String str : mNames) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(str);
            tabLayout.addTab(tab);
        }

        return mNames;
    }

}
