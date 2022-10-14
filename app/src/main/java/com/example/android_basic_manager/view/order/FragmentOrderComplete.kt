package com.example.android_basic_manager.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.databinding.FragmentOrderCompleteBinding
import com.example.android_basic_manager.viewmodel.ViewModelOrder

class FragmentOrderComplete : Fragment() {
    lateinit var binding : FragmentOrderCompleteBinding
    lateinit var mView : View
    lateinit var viewModel : ViewModelOrder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderCompleteBinding.inflate(inflater,container,false)
        mView =binding.root
        viewModel = MainActivity.mVOrder
        funcition()
        return mView
    }

    // tong hop cac funcition
    fun funcition(){
        viewModel.getDataOrderComplete()
        recycalInformationUser()
    }

    /// thong tin khach hang
    fun recycalInformationUser(){
        viewModel.getdataComplete()
        viewModel.liveOrderComplete.observe(viewLifecycleOwner){
            if (it!= null){
                val adapterInfor = AdapterInformation(it)
                adapterInfor.setClick(object :AdapterInformation.ClickButton{
                    override fun itemsOrder(position: Int) {
                        viewModel.nextAction.postValue("Detail Order")
                        viewModel.itemsInformation.postValue(it.get(position))

                        // lay ra chi tiet don hang cua user thong qua document id
                        viewModel.getDataItems(viewModel.listDataId[position])
                        viewModel.idOrder = viewModel.listDataId[position]
                    }

                })
                binding.allOrder.adapter = adapterInfor
                binding.allOrder.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }
        }
    }
}