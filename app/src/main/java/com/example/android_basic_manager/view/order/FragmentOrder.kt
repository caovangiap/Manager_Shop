package com.example.android_basic_manager.view.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentOrderBinding
import com.example.android_basic_manager.viewmodel.ViewModelOrder

class FragmentOrder : Fragment() {

    lateinit var binding: FragmentOrderBinding
    lateinit var mView: View
    lateinit var viewModel: ViewModelOrder
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        mView = binding.root
        viewModel = MainActivity.mVOrder
        binding.viewmodel = viewModel
        setUpFuncition()
        return mView
    }

    fun setUpFuncition() {
        getdata()

    }

    fun getdata() {
        viewModel.getdata()
        // data infor khác 0 để tránh trường hợp list data apdapter rỗng
        viewModel.liveInformation.observe(viewLifecycleOwner) {
            if (it.size != 0) {
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
                binding.allOrder.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            } else {
                Log.d("Fragment Order", "null")
            }
        }


    }


}