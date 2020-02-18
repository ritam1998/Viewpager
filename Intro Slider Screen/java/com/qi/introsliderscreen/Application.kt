package com.qi.introsliderscreen

class Application : android.app.Application() {

    private var preference : FirstTimePreference? = null
    private var app: Application? = null

    override fun onCreate() {
        super.onCreate()

        app = this
        preference = FirstTimePreference(this)
    }

    fun getApp(): Application? {
        return app
    }

    fun getPreference(): FirstTimePreference? {
        return preference
    }

    fun setPreference(prefs: FirstTimePreference) {
        this.preference = prefs
    }
}