package com.example.android_basic_manager.view.respone_client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentItemsResponseBinding
import com.example.android_basic_manager.viewmodel.ViewModelRespontClient

class FragmentResponeItems : Fragment() {
    lateinit var binding : FragmentItemsResponseBinding
    lateinit var mView : View
    lateinit var viewModel : ViewModelRespontClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemsResponseBinding.inflate(inflater,container,false)
        mView = binding.root
        viewModel = MainActivity.vMResponse
        setView()
        toolBar()
        return mView
    }

    fun setView(){
        viewModel.mess.observe(viewLifecycleOwner){
            binding.Text.text = it
        }
    }
    fun toolBar(){
        binding.ToolBar.inflateMenu(R.menu.toolbar)
        binding.ToolBar.setNavigationIcon(R.drawable.back)
//yêu cầu activity điều hướng quay trở lại man manager
        binding.ToolBar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("Manager")
        }
    }
}