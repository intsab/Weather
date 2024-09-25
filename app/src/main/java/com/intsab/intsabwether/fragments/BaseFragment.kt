package com.intsab.intsabwether.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.intsab.intsabwether.di.DaggerAppComponent
import dagger.android.support.DaggerFragment

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
open class BaseFragment: DaggerFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}