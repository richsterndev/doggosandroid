package com.richstern.doggos.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.richstern.doggos.R
import com.richstern.doggos.view.MainViewPagerAdapter
import com.richstern.doggos.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private val viewPager by lazy { findViewById<ViewPager2>(R.id.main_view_pager) }
    private val tabLayout by lazy { findViewById<TabLayout>(R.id.main_tab_layout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel

        viewPager.adapter = MainViewPagerAdapter(this)
    }
}