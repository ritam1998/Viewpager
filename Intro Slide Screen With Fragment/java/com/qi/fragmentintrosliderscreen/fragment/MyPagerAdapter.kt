package com.qi.fragmentintrosliderscreen.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPagerAdapter(fm: FragmentManager, var allFragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return allFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return allFragmentList[position]
    }
}