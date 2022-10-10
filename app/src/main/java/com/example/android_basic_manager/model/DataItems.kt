package com.example.android_basic_manager.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class DataItems(
    @SerializedName("Id_product")
    var id: Long? = null,
    @SerializedName("Name")
    var name_Product: String? = null,
    @SerializedName("Price")
    var Price: String? = null,
    @SerializedName("Condition")
    var Condition: String? = null,
    @SerializedName("Image")
    var Image: String? = null,
    @SerializedName("Size")
    var Size: String? = null
): Parcelable
