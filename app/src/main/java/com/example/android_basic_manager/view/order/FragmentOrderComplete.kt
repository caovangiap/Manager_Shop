package com.example.android_basic_manager.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic_manager.databinding.FragmentOrderCompleteBinding

class FragmentOrderComplete : Fragment() {
    lateinit var binding : FragmentOrderCompleteBinding
    lateinit var mView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderCompleteBinding.inflate(inflater,container,false)
        mView =binding.root
        return mView
    }
}