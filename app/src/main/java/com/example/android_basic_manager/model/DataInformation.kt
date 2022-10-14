package com.example.android_basic_manager.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class DataInformation(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("note")
    val note: String? = null,
    @SerializedName("phone_Number")
    val phoneNumber: String? = null,
    @SerializedName("timestamp")
    val timestamp: String? = null,
    @SerializedName("id_user")
    val id_user: String? = null,
    @SerializedName("total")
    val total: String? = null,
    @SerializedName("complate")
    val complate: String? = null
): Parcelable