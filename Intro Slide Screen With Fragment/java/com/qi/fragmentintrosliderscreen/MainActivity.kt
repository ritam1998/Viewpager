package com.qi.fragmentintrosliderscreen

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.qi.fragmentintrosliderscreen.fragment.*

class MainActivity : AppCompatActivity() {

    private var preferences: FirstTimePreference? = null
    private var myPagerAdapter : MyPagerAdapter? = null
    private var layoutsFragments = ArrayList<Fragment>()

    lateinit var dots : Array<TextView?>
    private var dotsLayout : LinearLayout? = null

    private var nextButton : Button? = null
    private var skipButton : Button? = null

    private var viewPager : ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_main)

        preferences = FirstTimePreference(this)

        if(preferences?.isFirstTime() == false){
            launchScreen()
        }else{

            viewPager = findViewById(R.id.viewpager)

            nextButton = findViewById(R.id.btn_next)

            skipButton = findViewById(R.id.btn_skip)

            dotsLayout = findViewById(R.id.layoutDots)

            layoutsFragments.add(FragmentOne())
            layoutsFragments.add(FragmentTwo())
            layoutsFragments.add(FragmentThree())
            layoutsFragments.add(FragmentFour())



            Log.d("all fragments"," "+layoutsFragments.size)

            myPagerAdapter = MyPagerAdapter(supportFragmentManager,layoutsFragments)

            viewPager?.adapter = myPagerAdapter

            changeStatusBarColor()

            addBottomDots(0)

            val viewPagerPageChangeListner = object : ViewPager.OnPageChangeListener {

                override fun onPageScrolled(position: Int,positionOffset: Float,positionOffsetPixels: Int) {}

                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageSelected(position: Int) {

                    addBottomDots(position)

                    if(position == layoutsFragments.size - 1){

                        nextButton?.setText("START NOW")
                        skipButton?.visibility = View.GONE
                    }else{

                        nextButton?.setText("Next")
                        skipButton?.visibility = View.VISIBLE
                    }
                }
            }

            Log.d("Change Listner",""+viewPagerPageChangeListner)

            Log.d("page change",""+viewPager?.addOnPageChangeListener(viewPagerPageChangeListner))

            nextButton?.setOnClickListener {

                val currentPageItem : Int? = getItem(+1)

                Log.d("Current Position",""+currentPageItem)

                Log.d("Layouts size",""+layoutsFragments.size)


                if(currentPageItem != null){
                    if(currentPageItem < layoutsFragments.size){
                        viewPager?.currentItem = currentPageItem
                    }else{
                        launchScreen()
                    }
                }
            }
            skipButton?.setOnClickListener {
                launchScreen()
            }
        }
    }
    private fun getItem(currentItemValue : Int?) : Int?{
        return viewPager?.currentItem?.plus(currentItemValue?:0)
    }
    private fun launchScreen(){

        preferences?.setFirstTimeLaunch(false)
        startActivity(Intent(this,HomeActivity :: class.java))
        finish()
    }
    private fun changeStatusBarColor(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            val window : Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun addBottomDots(currentPage : Int){

        dots = arrayOfNulls(layoutsFragments.size)

        val colorActive = resources.getIntArray(R.array.array_dot_active)
        val  colorInactive = resources.getIntArray(R.array.array_dot_inactive)

        dotsLayout?.removeAllViews()

        for(i in dots.indices){

            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("•")
            dots[i]?.textSize = 35F
            dots[i]?.setTextColor(colorInactive[currentPage])
            dotsLayout?.addView(dots[i])
        }
        if(dots.isEmpty()){
            dots[currentPage]?.setTextColor(colorActive[currentPage])
        }
    }
}

