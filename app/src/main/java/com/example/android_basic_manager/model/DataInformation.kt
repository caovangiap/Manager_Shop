package com.example.android_basic_manager.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataInformation(
    val name: String? = null,
    val address: String? = null,
    val note: String? = null,
    val phoneNumber: String? = null,
    val timestamp: String? = null,
    val id_user: String? = null,
    val total: String? = null,
    val complate: String
): Parcelable