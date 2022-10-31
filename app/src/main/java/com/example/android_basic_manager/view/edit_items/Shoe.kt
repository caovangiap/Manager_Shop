package com.example.android_basic_manager.view.edit_items

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.databinding.FragmentProductShoeBinding
import com.example.android_basic_manager.model.DataShoe
import com.example.android_basic_manager.viewmodel.ViewModelAddItems
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Shoe : Fragment() {
    lateinit var binding: FragmentProductShoeBinding
    lateinit var mView: View
    lateinit var viewModel: ViewModelAddItems
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductShoeBinding.inflate(inflater, container, false)
        viewModel = MainActivity.vMAddItems
        mView = binding.root
        setUpView()
        return mView
    }

    fun setUpView() {
        viewProduct()
    }

    // hiển thị toàn bộ sản phẩm
    fun viewProduct() {
        val data = mutableListOf<DataShoe>()
        viewModel.docName.clear()
        // lấy data từ firebase về
        db.collection("Product")
            .document("Shoe").collection("AllShoe")

            .whereEqualTo("Size", "30")
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in snapshot!!) {
                    doc.toObject(DataShoe::class.java).let { data.add(it) }
                    viewModel.docName.add(doc.id)

                    Log.d("shoe",doc.id)
                }

                // đẩy data lên view
                val adapterShoe = AdapterProduct(data)
                adapterShoe.listener(object : AdapterProduct.Setonclick {
                    override fun onclick(position: Int) {
                        viewModel.nextAction.postValue("DetailShoe")
                        Log.d("Shoe", "click")
//                        // view model nhận position cua dataitems hien thi len detail
                        viewModel.dataItemsShoe(data[position],viewModel.docName[position])

                    }
                })
                binding.viewItem.adapter = adapterShoe
                binding.viewItem.layoutManager = StaggeredGridLayoutManager(
                    2,
                    GridLayoutManager.VERTICAL
                )

            }

    }



}
