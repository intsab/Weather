package com.intsab.intsabwether

import com.intsab.intsabwether.di.AppComponent
import com.intsab.intsabwether.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlinx.coroutines.DelicateCoroutinesApi


/**
 * Created by intsabhaider
 * on 24,September,2024
 */
class WeatherApp : DaggerApplication() {
    var appComponent: AppComponent? = null

    @DelicateCoroutinesApi
    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.factory().create(this)
        }
        return appComponent as AppComponent
    }
}