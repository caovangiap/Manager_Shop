package com.example.android_basic_manager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_basic_manager.viewmodel.ViewModelRespontClient
import com.example.android_basic_manager.ulis.StorageLogin
import com.example.android_basic_manager.view.addItems.FragmentAddItems
import com.example.android_basic_manager.view.allmanager.FragmentManager
import com.example.android_basic_manager.view.edit_items.EditItems
import com.example.android_basic_manager.view.edit_items.FragmentEdit
import com.example.android_basic_manager.view.login.Login
import com.example.android_basic_manager.view.order.FragmentDetaillOrder
import com.example.android_basic_manager.view.order.FragmentFuncitionOrder
import com.example.android_basic_manager.view.respone_client.FragmentResponeItems
import com.example.android_basic_manager.view.respone_client.RespontClient
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
        lateinit var vMResponse : ViewModelRespontClient
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApplicationContext = this
        vMAddItems = ViewModelAddItems()
        vMLogin = ViewModelLogin()
        mVManager = ViewModelManager()
        mVOrder = ViewModelOrder()
        vMResponse = ViewModelRespontClient()
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
        // respone
        fResponse()
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
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = EditItems()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "Order"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentFuncitionOrder()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "Response"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = RespontClient()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
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
                "DetailShoe"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentEdit()
                    manager.addToBackStack(null)
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }


            }
        }
    }

    fun fResponse(){
        vMResponse.nextAction.observe(this){
            when(it){
                "Manager"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentManager()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "Detail"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentResponeItems()
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
                "Response"->{
                    val manager = supportFragmentManager.beginTransaction()
                    val fragment = FragmentResponeItems()
                    manager.addToBackStack(null)
                    manager.replace(R.id.VIew,fragment)
                    manager.commit()
                }
            }
        }
    }
}