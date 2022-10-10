package com.example.android_basic_manager.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.databinding.FragmentLoginBinding
import com.example.android_basic_manager.viewmodel.ViewModelLogin

class Login : Fragment() {

    lateinit var binding : FragmentLoginBinding
    lateinit var mView : View
    lateinit var viewModel : ViewModelLogin
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        mView = binding.root
        viewModel = MainActivity.vMLogin
        binding.viewModel = viewModel
        helpText()
        return mView
    }

    // hiển thị các chú ý
    fun helpText(){
        viewModel.helpName.observe(viewLifecycleOwner){
            binding.layouName.helperText = it
        }
        viewModel.helpPass.observe(viewLifecycleOwner){
            binding.layoutPass.helperText = it
        }
    }
}