package com.mokhtar.currencyconverterapp.util

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.mokhtar.currencyconverterapp.model.convert.ConvertData
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.convert.ValueData
import org.json.JSONObject
import java.lang.reflect.Type


object GsonTypeHelper {


    fun create(): Gson = GsonBuilder().apply {

        registerTypeAdapter(ConvertResponse::class.java, ConvertType())
        setLenient()
    }.create()


    private class ConvertType : JsonDeserializer<ConvertResponse> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,

            context: JsonDeserializationContext?
        ): ConvertResponse {
            val list = mutableListOf<ConvertData>()
            val innerJsonObject = json?.asJsonObject?.getAsJsonObject("results")
            val innerKeys = innerJsonObject?.asJsonObject?.keySet()

            innerKeys?.forEach { key ->

                val mainData = innerJsonObject[key] as JsonObject
                val jsonKeys = (mainData["val"] as JsonObject).asJsonObject.keySet()
                    //.keys()


                val convertObject = ConvertData.fillInData(mainData["fr"].toString(),mainData["id"].toString(),mainData["to"].toString())

                 val jsonVals = mutableListOf<ValueData>()

                jsonKeys.forEach {
                   val jsonVal =  ((innerJsonObject[key] as JsonObject)["val"] as JsonObject)[it].asDouble
                    jsonVal?.let {
                        jsonVals.add(ValueData(it))
                    }
                }


                convertObject.valueData = jsonVals

            list.add(convertObject)

            }
            return ConvertResponse(list)
        }
    }

}