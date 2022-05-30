package com.mokhtar.currencyconverterapp.model.convert


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConvertResponse(
//    @SerializedName("date")
//    val date: String,
//    @SerializedName("query")
//    val query: Query,
    @SerializedName("results")
    val convertData: MutableList<ConvertData>
): Parcelable