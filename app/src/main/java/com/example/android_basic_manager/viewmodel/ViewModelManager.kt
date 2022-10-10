package com.example.android_basic_manager.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelManager : ViewModel() {

    var nextAction = MutableLiveData<String>()

    fun clickOrder(){
        nextAction.postValue("Order")
    }
    fun clickAddItems(){
        nextAction.postValue("AddItems")
    }

    fun clickEditItems(){
        nextAction.postValue("EditItems")
    }

    fun clickRespont(){
        nextAction.postValue("Response")
    }
}