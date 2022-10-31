package com.example.android_basic_manager.view.edit_items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentAllproductBinding
import com.example.android_basic_manager.viewmodel.ViewModelAddItems
import com.google.android.material.tabs.TabLayout

class EditItems : Fragment() {
    lateinit var binding : FragmentAllproductBinding
    lateinit var viewModel : ViewModelAddItems
    lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllproductBinding.inflate(inflater,container,false)
        tabLayout = binding.TabLayout
        viewModel = MainActivity.vMAddItems
        val mView = binding.root
        setUpView()
        return mView
    }

    fun setUpView(){
        tabLayout()
        toolBar()
    }

    // tab layout hien thi cac sản phẩm
    fun tabLayout(){
        val fragment_shoe = Shoe()
        val fragmentTransition = childFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.view, fragment_shoe)
        fragmentTransition.commit()
        val fragment_accessory = Accessory()
        tabLayout.addTab(tabLayout.newTab().setText("Shoe"), true)
        tabLayout.addTab(tabLayout.newTab().setText("Accessory"))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position
                when (position) {
                    0 -> {
                        val fragmentTransition = childFragmentManager.beginTransaction()
                        fragmentTransition.replace(R.id.view, fragment_shoe)
                        fragmentTransition.commit()
                    }
                    1 -> {
                        val fragmentTransition = childFragmentManager.beginTransaction()
                        fragmentTransition.replace(R.id.view, fragment_accessory)
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

    fun toolBar(){
        binding.myToolbar.inflateMenu(R.menu.toolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.back)
//yêu cầu activity điều hướng quay trở lại man manager
        binding.myToolbar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("Manager")
        }
    }
}