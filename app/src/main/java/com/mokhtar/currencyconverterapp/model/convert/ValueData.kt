package com.mokhtar.currencyconverterapp.model.convert

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ValueData(
    var totalValue: Double
):Parcelable