package com.mokhtar.currencyconverterapp.util

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mokhtar.currencyconverterapp.model.currency.Currency
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.model.currency.Results

class CurrencyConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromCurrencyDataToString(currencyData: Results): String {
        return gson.toJson(currencyData)
    }

    @TypeConverter
    fun fromStringToCurrencyData(data: String): Results {
        val typeToken = object : TypeToken<Results>() {}.type
        return gson.fromJson(data, typeToken)
    }

    @TypeConverter
    fun fromCurrencyToString(currency: Currency): String {
        return gson.toJson(currency)
    }

    @TypeConverter
    fun fromStringToCurrency(data: String): Currency {
        val typeToken = object : TypeToken<Currency>() {}.type
        return gson.fromJson(data, typeToken)
    }


    @TypeConverter
    fun fromCurrencyListToString(currency: List<Currency>): String {
        return gson.toJson(currency)
    }

    @TypeConverter
    fun fromStringToCurrencyList(data: String): List<Currency> {
        val typeToken = object : TypeToken<List<Currency>>() {}.type
        return gson.fromJson(data, typeToken)
    }


    @TypeConverter
    fun fromCurrencyResponseToString(currency: CurrencyResponse): String {
        return gson.toJson(currency)
    }

    @TypeConverter
    fun fromStringToCurrencyResponse(data: String): CurrencyResponse {
        val typeToken = object : TypeToken<CurrencyResponse>() {}.type
        return gson.fromJson(data, typeToken)
    }

}