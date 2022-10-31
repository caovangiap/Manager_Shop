package com.example.android_basic_manager.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewModelRespontClient : ViewModel() {

    val nextAction = MutableLiveData<String>()
    val db = Firebase.firestore

    val dataId = mutableListOf<String>()
    val dataResponse = mutableListOf<String>()
    val liveDataId = MutableLiveData<MutableList<String>>()
    val liveDataResponse = MutableLiveData<MutableList<String>>()
    // data items
    var itemsId = MutableLiveData<String>()
    var mess = MutableLiveData<String>()

    // lay data
    fun callData(){
        dataId.clear()
        dataResponse.clear()
        db.collection("User")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data.values}")
                    dataId.add(document.id)
                    dataResponse.add(document.data.values.toString())
                    liveDataId.value = dataId
                    liveDataResponse.value = dataResponse
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }
}