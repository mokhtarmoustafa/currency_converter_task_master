package com.mokhtar.currencyconverterapp.model.country


import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("results")
    val results: List<Country>
)