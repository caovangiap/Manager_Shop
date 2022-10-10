package com.example.android_basic_manager.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentFuncitionOrderBinding
import com.example.android_basic_manager.viewmodel.ViewModelOrder
import com.google.android.material.tabs.TabLayout

// các chức năng của màn order

class FragmentFuncitionOrder : Fragment() {
    lateinit var binding: FragmentFuncitionOrderBinding
    lateinit var mView: View
    lateinit var viewModel : ViewModelOrder
    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFuncitionOrderBinding.inflate(layoutInflater,container,false)
        mView = binding.root
        viewModel = MainActivity.mVOrder
        tabLayout = binding.TabLayout
        funcition()
        return mView
    }

    fun funcition(){
        // tablayout
        tabLayout()
        setUpFuncition()
    }

    fun tabLayout(){
        val fragment_Order = FragmentOrder()
        val fragmentTransition = childFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.view, fragment_Order)
        fragmentTransition.commit()
        
        val fragment_Complete = FragmentOrderComplete()
        tabLayout.addTab(tabLayout.newTab().setText("Order"), true)
        tabLayout.addTab(tabLayout.newTab().setText("Complete"))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                when (position) {
                    0 -> {
                        val fragmentTransition = childFragmentManager.beginTransaction()
                        fragmentTransition.replace(R.id.view, fragment_Order)
                        fragmentTransition.commit()
                    }
                    1 -> {
                        val fragmentTransition = childFragmentManager.beginTransaction()
                        fragmentTransition.replace(R.id.view, fragment_Complete)
                        fragmentTransition.commit()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
    fun setUpFuncition(){
        binding.myToolbar.inflateMenu(R.menu.alltoolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.back)
//yêu cầu activity điều hướng quay trở lại man manager
        binding.myToolbar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("Manager")
        }
    }
}