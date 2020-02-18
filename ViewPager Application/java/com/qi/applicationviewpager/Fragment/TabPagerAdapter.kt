package com.qi.applicationviewpager.Fragment

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter



class TabPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val arrayNameList = ArrayList<String>()
    private val allFragmentList = ArrayList<Fragment>()

    override fun getCount(): Int {
        return allFragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return allFragmentList[position]
    }

    fun addFragment(fragment: Fragment,title : String){
        allFragmentList.add(fragment)
        arrayNameList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrayNameList[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
}