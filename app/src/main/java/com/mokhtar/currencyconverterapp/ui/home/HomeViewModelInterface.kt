package com.mokhtar.currencyconverterapp.ui.home

import androidx.lifecycle.LiveData
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.util.State


interface HomeViewModelInterface{
    val currencyLiveData: LiveData<State<CurrencyResponse>>
    val convertLiveData: LiveData<State<ConvertResponse>>

    fun getCurrencies()
    fun getConvertData( query: String,
                        compact: String,
                        date: String,
                        endDate: String?)
}