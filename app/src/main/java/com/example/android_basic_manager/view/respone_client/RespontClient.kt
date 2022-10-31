package com.example.android_basic_manager.view.respone_client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentResponeClientBinding
import com.example.android_basic_manager.viewmodel.ViewModelRespontClient

class RespontClient : Fragment() {

    lateinit var binding : FragmentResponeClientBinding
    lateinit var mView : View
    lateinit var ViewModel : ViewModelRespontClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentResponeClientBinding.inflate(layoutInflater,container,false)
        mView = binding.root
        ViewModel = MainActivity.vMResponse
        allFuncition()
        ViewModel.callData()
        return mView
    }

    // all funcition
    fun allFuncition(){
        ViewModel.liveDataId.observe(viewLifecycleOwner){
            val adapter = AdapterResponse(it)
            adapter.setClick(object :AdapterResponse.ClickButton{
                override fun itemsOrder(position: Int) {
                    ViewModel.itemsId.value = ViewModel.dataId[position]
                    ViewModel.mess.value = ViewModel.dataResponse[position]
                    ViewModel.nextAction.postValue("Detail")
                }
            })
            binding.response.adapter = adapter
            binding.response.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }
        toolBar()

    }

    fun toolBar(){
        binding.myToolbar.inflateMenu(R.menu.toolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.back)
        //yêu cầu activity điều hướng quay trở lại man manager
        binding.myToolbar.setNavigationOnClickListener {
            ViewModel.nextAction.postValue("Manager")
        }
    }
}