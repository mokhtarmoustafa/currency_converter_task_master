package com.mokhtar.currencyconverterapp.data.remote.repository

import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.util.State
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow

@ActivityRetainedScoped
interface CurrencyResponseInterface{

    fun getAllCurrencies(): Flow<State<CurrencyResponse>>

    suspend fun getConvertData(
        query: String,
        compact: String,
        date: String,
        endDate: String?
    ): Flow<State<ConvertResponse>>




}