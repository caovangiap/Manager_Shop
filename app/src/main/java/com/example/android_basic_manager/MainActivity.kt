package com.example.android_basic_manager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_basic_manager.ulis.StorageLogin
import com.example.android_basic_manager.view.addItems.FragmentAddItems
import com.example.android_basic_manager.view.allmanager.FragmentManager
import com.example.android_basic_manager.view.login.Login
import com.example.android_basic_manager.view.order.FragmentDetaillOrder
import com.example.android_basic_manager.view.order.FragmentFuncitionOrder
import com.example.android_basic_manager.view.order.FragmentOrder
import com.example.android_basic_manager.viewmodel.ViewModelAddItems
import com.example.android_basic_manager.viewmodel.ViewModelLogin
import com.example.android_basic_manager.viewmodel.ViewModelManager
import com.example.android_basic_manager.viewmodel.ViewModelOrder

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var ApplicationContext : Context
        lateinit var vMLogin : ViewModelLogin
        lateinit var mVManager : ViewModelManager
        lateinit var mVOrder : ViewModelOrder
        lateinit var vMAddItems : ViewModelAddItems
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApplicationContext = this
        vMAddItems = ViewModelAddItems()
        vMLogin = ViewModelLogin()
        mVManager = ViewModelManager()
        mVOrder = ViewModelOrder()
        ManagerActiity()
    }

    // điêu hướng các màn
    fun ManagerActiity(){
        // mỗi lần khởi động mật khẩu và tên đăng nhập sẽ đc cài đặt về name và pass
        StorageLogin.SetUp("admin","admin")
        val manager = supportFragmentManager.beginTransaction()
        val fragment = Login()
        manager.replace(R.id.VIew,fragment)
        manager.commit()
        // màn login khi ms khởi động
        login()
        // Manager
        fManager()
        // order
        fOrder()
        // add items
        fAddItems()
    }

    // login
    fun login(){
        vMLogin.nextAction.observe(this){
            when(it){
                vMLogin.actionManager ->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentManager()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
            }

        }
    }

    // manager fragment
    fun fManager(){

        mVManager.nextAction.observe(this){
            when(it){
                "Login" ->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = Login()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "AddItems"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentAddItems()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "EditItems"->{

                }
                "Order"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentFuncitionOrder()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "Response"->{

                }
            }
        }
    }

    fun fOrder(){
        mVOrder.nextAction.observe(this){
            when(it){
                "Manager"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentManager()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "Detail Order"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentDetaillOrder()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "Order"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentFuncitionOrder()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
            }
        }
    }

    fun fAddItems(){
        vMAddItems.nextAction.observe(this){
            when(it){
                "Manager"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentManager()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
            }
        }
    }


}