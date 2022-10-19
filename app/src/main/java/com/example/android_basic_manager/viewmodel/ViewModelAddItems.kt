package com.example.android_basic_manager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelAddItems : ViewModel() {

    val nextAction = MutableLiveData<String>()
}