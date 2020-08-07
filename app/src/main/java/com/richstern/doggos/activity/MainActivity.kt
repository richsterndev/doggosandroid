package com.richstern.doggos.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.richstern.doggos.R
import com.richstern.doggos.view.MainViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewPager by lazy { findViewById<ViewPager2>(R.id.main_view_pager) }
    private val tabLayout by lazy { findViewById<TabLayout>(R.id.main_tab_layout) }
    private lateinit var adapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MainViewPagerAdapter(this)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = MainViewPagerAdapter.Tabs.values()[position].displayName
        }.attach()
    }
}