package com.mokhtar.currencyconverterapp.model.currency

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Currency (
    @SerializedName("currencyName")
    val currencyName: String,
    @SerializedName("currencySymbol")
    val currencySymbol: String?,
    @SerializedName("id")
    val id: String
):Parcelable
{
    val countryId:String get() = id.substring(0,2).lowercase(Locale.getDefault()).apply {
        when(this)
        {
            "xa"-> return "cf"
            "xo"-> return "sn"
            "xp"-> return "wf"
            "xc"->return "vc"

        }
    }
}

