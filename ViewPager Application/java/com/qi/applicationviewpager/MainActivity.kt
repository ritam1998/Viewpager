package com.qi.applicationviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.qi.applicationviewpager.Fragment.ContactsFragment
import com.qi.applicationviewpager.Fragment.RecentsFragment
import com.qi.applicationviewpager.Fragment.SpeedDialsFragment
import com.qi.applicationviewpager.Fragment.TabPagerAdapter

class MainActivity : AppCompatActivity() {

    private var tabLayout : TabLayout? = null
    private var viewPager : ViewPager? = null

    private val tabPagerAdapter = TabPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabItem)

        tabPagerAdapter.addFragment(SpeedDialsFragment(),"Speed Dial")
        tabPagerAdapter.addFragment(RecentsFragment(),"Recents")
        tabPagerAdapter.addFragment(ContactsFragment(),"Contacts")

        viewPager = findViewById(R.id.viewpager)

        viewPager?.adapter = tabPagerAdapter

        tabLayout?.setupWithViewPager(viewPager)
    }
}
