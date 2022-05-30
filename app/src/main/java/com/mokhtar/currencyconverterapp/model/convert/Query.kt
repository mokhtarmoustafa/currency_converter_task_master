package com.mokhtar.currencyconverterapp.model.convert


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Query(
    @SerializedName("count")
    val count: Int
):Parcelable