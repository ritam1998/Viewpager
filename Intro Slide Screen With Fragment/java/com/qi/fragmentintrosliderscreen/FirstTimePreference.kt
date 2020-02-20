package com.qi.fragmentintrosliderscreen

import android.content.Context
import android.content.SharedPreferences

class FirstTimePreference {

    private var sharedPreferences : SharedPreferences? = null
    private var editor : SharedPreferences.Editor? = null
    private var _context : Context? = null

    var PRIVATE_MODE : Int? = 0
    var PREF_NAME = "fragmentIntoSliderScreen"
    var IS_FIRST_TIME = "IsFirstTime"

    constructor(context: Context){
        this._context = context
        sharedPreferences = _context?.getSharedPreferences(PREF_NAME,PRIVATE_MODE?:0)
    }

    fun setFirstTimeLaunch(isFirstTime : Boolean){

        editor = sharedPreferences?.edit()
        editor?.putBoolean(IS_FIRST_TIME,isFirstTime)
        editor?.apply()
        editor?.commit()
    }

    fun isFirstTime() : Boolean{
        return sharedPreferences?.getBoolean(IS_FIRST_TIME,true) == true
    }
}