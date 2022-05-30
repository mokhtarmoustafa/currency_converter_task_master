package com.mokhtar.currencyconverterapp.model.convert


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConvertData(
    @SerializedName("fr")
    var fr: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("to")
    var to: String,
    @SerializedName("val")
    var valueData: MutableList<ValueData>,
    var isExpanded: Boolean
):Parcelable {




    companion object{
        fun fillInData(fr: String, id: String, to: String): ConvertData{
            return ConvertData(fr,id,to, mutableListOf(),false)
        }
    }
}