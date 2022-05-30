package com.mokhtar.currencyconverterapp.util

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.mokhtar.currencyconverterapp.model.convert.ConvertData
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.currency.Currency
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import java.lang.reflect.Type

object GsonHelper {


    fun create(): Gson = GsonBuilder().apply {
        registerTypeAdapter(CurrencyResponse::class.java, CurrencyType())
        setLenient()
    }.create()

    private class CurrencyType : JsonDeserializer<CurrencyResponse> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): CurrencyResponse {
            val list = mutableListOf<Currency>()
            val innerJsonObject = json?.asJsonObject?.getAsJsonObject("results")
            val innerKeys = innerJsonObject?.asJsonObject?.keySet()
            innerKeys?.forEach { key ->

                // Get your item with key
                val item = Gson().fromJson<Currency>(
                    innerJsonObject.asJsonObject[key],
                    object : TypeToken<Currency>() {}.type
                )

                list.add(item)
            }
            return CurrencyResponse(list)
        }
    }





}