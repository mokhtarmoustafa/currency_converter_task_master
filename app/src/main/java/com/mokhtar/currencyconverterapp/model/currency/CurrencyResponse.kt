package com.mokhtar.currencyconverterapp.model.currency

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Entity(tableName = TABLE_NAME)
@Parcelize
data class CurrencyResponse(
    @SerializedName("results")
    val results: MutableList<Currency>
): Parcelable
{
    @PrimaryKey(autoGenerate = false)
    var id = 0
    companion object
    {
        const val TABLE_NAME="TBL_Currencies"
    }

}






