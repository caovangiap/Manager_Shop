package com.example.android_basic_manager.view.addItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.databinding.FragmentAddItemsBinding
import com.example.android_basic_manager.viewmodel.ViewModelAddItems

class FragmentAddItems : Fragment() {

    lateinit var binding : FragmentAddItemsBinding
    lateinit var mView: View
    lateinit var ViewModel : ViewModelAddItems

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddItemsBinding.inflate(inflater,container,false)
        mView = binding.root
        ViewModel = MainActivity.vMAddItems
        return mView
    }
}