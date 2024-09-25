package com.intsab.intsabwether.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by intsabhaider
 * on 25,September,2024
 */
open class BaseViewModel @Inject constructor() : ViewModel() {
    val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

}