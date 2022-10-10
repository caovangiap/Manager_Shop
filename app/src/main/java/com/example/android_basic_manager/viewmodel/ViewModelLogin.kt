package com.example.android_basic_manager.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import com.example.android_basic_manager.ulis.StorageLogin

class ViewModelLogin : BaseObservable() {


    // chuyen sang man manager
    val actionManager = "ActionManager"

    @get:Bindable
    var inputName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputName)
        }

    @get:Bindable
    var inputPass: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.inputPass)
        }

    var helpName = MutableLiveData<String>()
    var helpPass = MutableLiveData<String>()
    var nextAction = MutableLiveData<String>()

    // fun lOGIN
    fun login() {
        val name = StorageLogin.getString(StorageLogin.UserName)
        val pass = StorageLogin.getString(StorageLogin.Password)
        if (inputName != name) {
            helpName.postValue("sai ten dang nhap")
        }
        if (inputPass != pass) {
            helpPass.postValue("sai mat khau")
        }
        if (inputPass == name && inputPass == pass) {
            helpPass.postValue("")
            helpName.postValue("")
            nextAction.postValue(actionManager)
        }
    }

}