package com.example.android_basic_manager.viewmodel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_basic_manager.model.DataInformation
import com.example.android_basic_manager.model.DataItems
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewModelOrder : ViewModel() {

    var nextAction = MutableLiveData<String>()
    val db = Firebase.firestore

    // data thoong tin khach hang
    val dataInformation = mutableListOf<DataInformation>()

    //  live data information
    val liveInformation = MutableLiveData<MutableList<DataInformation>>()

    // data list danh sách hàng khách order
    var detaiData = mutableListOf<DataItems>()


    // manng chua id duoc post vao dataId live data cho fragment
    val listDataId = mutableListOf<String>()

    // data cho man information
    val itemsInformation = MutableLiveData<DataInformation>()
    val itemsProductOrder = MutableLiveData<MutableList<DataItems>>()

    // id của đơn hàng ở màn detail
    lateinit var idOrder: String
    // trạng thái của đơn hàng khi cập nhật
    var ConditionOrder = 0

    // lay data order
    fun getdata() {
        // clear list trc khi lay data tranh truong hop bi trung du lieu da tung goi
        listDataId.clear()
        // clear list infor trc khi goi lai tranh truong hop bi trung
        dataInformation.clear()
        // data nguoi mua hang chua dc gui don hang
        db.collection("Order")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val time = document.data.get("timestamp")
                    val name = document.data.get("name")
                    val address = document.data.get("address")
                    val note = document.data.get("note")
                    val phoneNumber = document.data.get("phoneNumber")
                    val id = document.data.get("id_user")
                    val total = document.data.get("total")
                    val complete = document.data.get("complate")

                    // id của đơn hàng
                    val id_Order = document.id
                    dataInformation.add(
                        DataInformation(
                            name as String?,
                            address as String?,
                            note as String?,
                            phoneNumber as String?,
                            time as String?,
                            id as String?,
                            total as String?,
                            complete as String
                        )
                    )
                    listDataId.add(id_Order)

                    // live data du lieu khach hang
                    liveInformation.postValue(dataInformation)
                }
                // get data tung items
                Log.d("test", dataInformation.toString())
            }
            .addOnFailureListener { exception ->
                Log.d("test", "Error getting documents: ", exception)
            }
    }

    fun getDataItems(dataId: String) {
        detaiData.clear()
        db.collection("Order")
            .document(dataId)
            .collection("product")
            .whereEqualTo("condition", "true")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }
                // chi tiet mat hang
                for (doc in snapshot!!) {
                    doc.toObject(DataItems::class.java).let { detaiData.add(it) }
                    itemsProductOrder.postValue(detaiData)
                }
            }
    }

    fun updateConditionOrder(id: String) {

        if (ConditionOrder == 0){
            val update = db.collection("Order").document(id)
            update
                .update("Complate", "True")
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            ConditionOrder = 1
        }
        else{
            val update = db.collection("Order").document(id)
            update
                .update("Complate", "False")
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
            ConditionOrder = 0
        }
    }
}