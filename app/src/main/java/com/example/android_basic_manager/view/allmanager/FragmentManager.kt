package com.example.android_basic_manager.view.allmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentManagerBinding
import com.example.android_basic_manager.viewmodel.ViewModelManager
import com.example.android_basic_manager.viewmodel.ViewModelOrder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.days

class FragmentManager : Fragment() {
    lateinit var binding: FragmentManagerBinding
    lateinit var mView: View
    lateinit var viewModel: ViewModelManager
    lateinit var vMOrder : ViewModelOrder
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManagerBinding.inflate(inflater, container, false)
        mView = binding.root
        viewModel = MainActivity.mVManager
        vMOrder = MainActivity.mVOrder
        binding.viewModel = viewModel
        setUpFuncition()
        return mView
    }

    // tổng hợp các chức năng
    fun setUpFuncition() {
        setUpToolBar()
        viewDate()
    }

    // xét sự kiện cho toolbar
    fun setUpToolBar() {

        binding.ToolBar.inflateMenu(R.menu.toolbar)

        binding.ToolBar.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.logOut -> {
                    viewModel.nextAction.postValue("Login")
                    true
                }
                else -> false
            }
        }
    }

    // hiển thị ngày tháng năm
    fun viewDate() {

        binding.Today.text =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }


}