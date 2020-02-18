package com.qi.introsliderscreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class FirstTimePreference  {

    private var preference : SharedPreferences? = null
    private var editor : SharedPreferences.Editor? = null
    private var context : Context? = null
    private var PRIVATE_MODE : Int = 0

    private val PREF_NAME = "spaceo-demo"
    private val IS_FIRST_TIME = "IsFirstTime"

    constructor(context: Context){
        this.context = context
        preference = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    }

    fun setFirstTimeLaunch(isFirstTime : Boolean){
        editor = preference?.edit()
        editor?.putBoolean(IS_FIRST_TIME,isFirstTime)
        editor?.apply()
        editor?.commit()
        Log.e("ValueFirst",""+isFirstTime())
    }

    fun isFirstTime(): Boolean {
        return preference?.getBoolean(IS_FIRST_TIME,true) == true
    }
}