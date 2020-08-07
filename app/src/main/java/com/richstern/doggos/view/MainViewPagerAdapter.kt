package com.richstern.doggos.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.richstern.doggos.randomimagequiz.fragment.QuizFragment
import com.richstern.doggos.randomimagequiz.fragment.QuizHistoryFragment

class MainViewPagerAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return Tabs.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (Tabs.values()[position]) {
            Tabs.QUIZ -> QuizFragment.newInstance()
            Tabs.QUIZ_HISTORY -> QuizHistoryFragment.newInstance()
        }
    }

    enum class Tabs(val displayName: String) {
        QUIZ("Quiz"),
        QUIZ_HISTORY("History")
    }
}