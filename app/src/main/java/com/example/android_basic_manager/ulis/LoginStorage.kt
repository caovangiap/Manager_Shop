package com.example.android_basic_manager.ulis

import android.content.Context
import android.util.Log
import com.example.android_basic_manager.MainActivity


// object lưu mật khẩu cục bộ
object StorageLogin {

    const val SHARED_PREFERENCES_NAME = "Demo"
    // tên đăng nhập
    const val UserName = "UserName"
    // mật khẩu
    const val Password = "UserPass"


    private  val Share = MainActivity.ApplicationContext.getSharedPreferences(
        SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    private val edit = Share.edit()

    fun SetUp(Name: String, Pass : String){
        Share.edit().clear().apply()
        edit.putString(UserName, Name)
        edit.putString(Password, Pass)
        edit.apply()
        Log.d("Login", edit.toString())
    }

    fun getString(key: String,ValueDefault: String?= null): String?{
        return Share.getString(key,ValueDefault)
    }
    fun PutKey(Key:String,Value: String) {
        return edit.putString(Key,Value).apply()
    }

    fun Remove(Key:String){
        edit.remove(Key)
    }

}