package com.example.android_basic_manager.view.order

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.R
import com.example.android_basic_manager.databinding.FragmentDetailItemsBinding
import com.example.android_basic_manager.model.DataInformation
import com.example.android_basic_manager.model.DataItems
import com.example.android_basic_manager.viewmodel.ViewModelOrder

class FragmentDetaillOrder : Fragment() {

    lateinit var binding : FragmentDetailItemsBinding
    lateinit var mView : View
    lateinit var viewModel : ViewModelOrder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailItemsBinding.inflate(inflater,container,false)
        mView = binding.root
        viewModel = MainActivity.mVOrder
        funcition()
        return mView
    }

    fun funcition(){
        // fun toolbar
        toolBar()
        // detail đơn hàng
        detailOrder()
        // access đơn hàng
        accessOrder()
    }

    fun toolBar(){
        binding.myToolbar.inflateMenu(R.menu.alltoolbar)
        binding.myToolbar.setNavigationIcon(R.drawable.back)
//yêu cầu activity điều hướng quay trở lại man manager
        binding.myToolbar.setNavigationOnClickListener {
            viewModel.nextAction.postValue("Order")
        }
    }

    // hiển thị chi tiết đơn hàng
    @SuppressLint("SetTextI18n")
    fun detailOrder(){
        viewModel.itemsProductOrder.observe(viewLifecycleOwner){
            if (it!= null){
                binding.items.adapter = AdapterDetaiOrder(it)
                binding.items.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }
        }
        viewModel.itemsInformation.observe(viewLifecycleOwner){
            if (it!=null){
                binding.name.text = "Tên khách hàng :${it.name}"
                binding.address.text = "Địa chỉ: ${it.address}"
                binding.phone.text = "SĐT : ${it.phoneNumber}"
                binding.Note.text = "Note: ${it.note}"
                binding.price.text = "Tổng Tiền : ${it.total}"
                Log.d("test",it.total.toString())
            }
        }
    }

    // xác nhận đơn hàng đã đc chuyển đi hoặc chưa

    fun accessOrder(){
        binding.Access.setOnClickListener {
            viewModel.updateConditionOrder(viewModel.idOrder)
            if (viewModel.ConditionOrder==1){
                binding.remove.setImageResource(R.drawable.ic_baseline_check_24)
                binding.remove.setColorFilter(com.google.android.material.R.color.material_blue_grey_900)
            }
            else{
                binding.remove.setImageResource(R.drawable.ic_baseline_cancel_24)
                binding.remove.setColorFilter(0xFFFF1744.toInt(), PorterDuff.Mode.MULTIPLY )
            }
        }
    }


}