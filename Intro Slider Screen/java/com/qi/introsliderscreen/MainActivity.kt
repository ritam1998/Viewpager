package com.qi.introsliderscreen

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.widget.TextView
import android.widget.Button
import android.widget.LinearLayout
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.os.Build
import android.text.Html
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import android.view.LayoutInflater



class MainActivity : AppCompatActivity() {

    private var myViewPagerAdapter : MyViewPagerAdapter? = null
    private var viewPager : ViewPager? = null

    lateinit var dots: Array<TextView?>
    private var dotsLayout : LinearLayout? = null
    private lateinit var layouts : Array<Int>

    private var preference : FirstTimePreference? = null
    private var buttonNext : Button? = null
    private var buttonSkip : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_main)

        preference = (application as Application).getPreference()

        if(preference?.isFirstTime() == false){
            Log.e("FirstTime",""+preference?.isFirstTime() )
            launchScreen()
        }else {

            Log.d("check", "value : ${preference?.isFirstTime()}")
            viewPager = findViewById(R.id.view_pager)

            dotsLayout = findViewById(R.id.layoutDots)

            buttonNext = findViewById(R.id.btn_next)

            buttonSkip = findViewById(R.id.btn_skip)

            layouts = arrayOf(
                R.layout.welcome_screen1,
                R.layout.welcome_screen2,
                R.layout.welcome_screen3
            )

            /* For showing Dots*/
            addBottomDots(0)

            /* For chaning Status Bar Color*/
            changeStatusBarColor()

            myViewPagerAdapter = MyViewPagerAdapter()
            viewPager?.adapter = myViewPagerAdapter

            val viewPagerPageChangeListener = object : ViewPager.OnPageChangeListener {

                override fun onPageSelected(position: Int) {
                    addBottomDots(position)

                    // changing the next button text 'NEXT' / 'GOT IT'

                    if (position == layouts.size - 1) {

                        // last page. make button text to GOT IT

                        buttonNext?.setText(getString(R.string.start))
                        buttonSkip?.setVisibility(View.GONE)

                    } else {

                        // still pages are left
                        buttonNext?.setText(getString(R.string.next))
                        buttonSkip?.setVisibility(View.VISIBLE)
                    }
                }

                override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
                override fun onPageScrollStateChanged(arg0: Int) {}
            }

            viewPager?.addOnPageChangeListener(viewPagerPageChangeListener)

            buttonNext?.setOnClickListener {

                val current: Int? = getItem(+1)

                if (current != null) {
                    if (current < layouts.size) {
                        viewPager?.currentItem = current
                    } else {
                        launchScreen()
                    }
                }
            }

            buttonSkip?.setOnClickListener {
                launchScreen()
            }
        }
    }

    private fun getItem(i : Int?): Int? {
        return viewPager?.currentItem?.plus(i?:0)
    }

    private fun launchScreen(){
        preference?.setFirstTimeLaunch(false)
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

    private fun addBottomDots(currentPage: Int) {

        dots = kotlin.arrayOfNulls(layouts.size)

        val colorsActive = resources.getIntArray(R.array.array_pager_active)
        val colorsInactive = resources.getIntArray(R.array.array_pager_inactive)

        dotsLayout?.removeAllViews()

        for (i in dots.indices) {

            dots[i] = TextView(this)
            dots[i]?.text = Html.fromHtml("•")
            dots[i]?.textSize = 35F
            dots[i]?.setTextColor(colorsInactive[currentPage])
            dotsLayout?.addView(dots[i])

        }
        if (dots.isNotEmpty())
            dots[currentPage]?.setTextColor(colorsActive[currentPage])
    }

    private fun changeStatusBarColor()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    inner class MyViewPagerAdapter : PagerAdapter(){

        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater!!.inflate(layouts[position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}
