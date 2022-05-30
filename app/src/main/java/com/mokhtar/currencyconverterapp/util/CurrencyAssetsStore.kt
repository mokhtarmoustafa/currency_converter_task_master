package com.mokhtar.currencyconverterapp.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.currency.Currency
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import kotlinx.coroutines.flow.flow
import java.io.IOException

object CurrencyAssetsStore {
    private lateinit var currencyList: List<Currency>
    private val gson = Gson()

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getALlCurrencies(context: Context) = flow<CurrencyResponse> {
        val jsonData = getJsonDataFromAsset(context, "currencies.json")
        val types = object : TypeToken<CurrencyResponse>() {}.type
        val currencyList:CurrencyResponse = gson.fromJson(jsonData, types)
        emit(currencyList)
    }

    fun getALlConvertCurrencies(context: Context) = flow<ConvertResponse> {
        val jsonData = getJsonDataFromAsset(context, "hestorical_data_usd_php.json")
        val types = object : TypeToken<ConvertResponse>() {}.type
        val currencyList:ConvertResponse = gson.fromJson(jsonData, types)
        emit(currencyList)
    }



}

