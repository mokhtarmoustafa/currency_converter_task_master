package com.mokhtar.currencyconverterapp.data.remote.repository

import com.mokhtar.currencyconverterapp.model.convert.ConvertData
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.convert.ValueData
import com.mokhtar.currencyconverterapp.model.currency.Currency
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.util.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockCurrencyRepo:CurrencyResponseInterface{

    val currencyResponse = CurrencyResponse(mutableListOf(Currency("ABC","BBB","IDID")))
   val convertResponse= ConvertResponse(mutableListOf(ConvertData("A","B","T", mutableListOf(ValueData(231313.0)),false)))

    override fun getAllCurrencies(): Flow<State<CurrencyResponse>> {
        return flow {
            emit(State.success(currencyResponse))
        }
    }

    override suspend fun getConvertData(
        query: String,
        compact: String,
        date: String,
        endDate: String?
    ): Flow<State<ConvertResponse>> {

//        val y = ConvertResponse(mutableListOf(ConvertData("A","B","T", mutableListOf(ValueData(231313.0)),false)))
        return flow{
            emit(State.success(convertResponse))
        }

    }


}