package com.example.android_basic_manager.viewmodel

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic_manager.MainActivity
import com.example.android_basic_manager.model.AccessoryData
import com.example.android_basic_manager.model.DataShoe
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class ViewModelAddItems : ViewModel() {


    val nextAction = MutableLiveData<String>()
    val shoeItems = mutableListOf<DataShoe>()
    val accessoryItems = mutableListOf<AccessoryData>()

    // live data
    val liveShoe = MutableLiveData<MutableList<DataShoe>>()
    val liveAccessoryData = MutableLiveData<MutableList<AccessoryData>>()

    // document gửi dữ liệu firebase
    var docName = mutableListOf<String>()
    var docNameItems: String? = null
    val db = Firebase.firestore


    // value cua man edit
    val nameProduct = MutableLiveData<String?>()
    val priceProduct = MutableLiveData<String?>()
    val ImageProduct = MutableLiveData<String?>()

    // check thong tin dau vao và up data sản pham
    fun allProduct() {
        shoeItems.clear()
        accessoryItems.clear()
        // lấy data từ firebase về
        db.collection("Product")
            .document("Shoe").collection("AllShoe")

            .whereEqualTo("Size", "30")
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                for (doc in snapshot!!) {
                    doc.toObject(DataShoe::class.java).let { shoeItems.add(it) }
                    liveShoe.value = shoeItems
                }
            }

        db.collection("Product")
            .document("Accessory").collection("AllAccessory")

            .whereEqualTo("Condition", "true")
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                for (doc in snapshot!!) {
                    doc.toObject(AccessoryData::class.java).let { accessoryItems.add(it) }
                    liveAccessoryData.value = accessoryItems
                }
            }
    }

    fun dataItemsShoe(dataItems: DataShoe, document: String) {
        nameProduct.value = dataItems.Name
        priceProduct.value = dataItems.Price
        ImageProduct.value = dataItems.Image.URL1.IG1
        docNameItems = document
    }

    fun dataItemsAccessory(dataItems: AccessoryData, document: String) {
        nameProduct.value = dataItems.Name
        priceProduct.value = dataItems.Price
        ImageProduct.value = dataItems.Image
        docNameItems = document
    }

}