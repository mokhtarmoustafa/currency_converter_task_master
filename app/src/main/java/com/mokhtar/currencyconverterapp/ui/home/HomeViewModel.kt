package com.mokhtar.currencyconverterapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokhtar.currencyconverterapp.data.remote.repository.CurrencyRepository
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.model.currency.Results
import com.mokhtar.currencyconverterapp.ui.base.BaseViewModel
import com.mokhtar.currencyconverterapp.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val currencyRepository: CurrencyRepository) :
    BaseViewModel() {
    //region variables
    private val _currencyLiveData = MutableLiveData<State<CurrencyResponse>>()
    val currencyLiveData: LiveData<State<CurrencyResponse>>
        get() = _currencyLiveData

    private val _convertLiveData = MutableLiveData<State<ConvertResponse>>()
    val convertLiveData: LiveData<State<ConvertResponse>>
        get() = _convertLiveData
    //endregion

    //region helper functions

     fun getCurrencies() {
        viewModelScope.launch {
            currencyRepository.getAllCurrencies() .collectLatest {
                _currencyLiveData.value = it
            }
        }
    }

     fun getConvertData( query: String,
                                compact: String,
                                date: String,
                                endDate: String?)
    {
        viewModelScope.launch {

           currencyRepository.getConvertData(query,compact,date,endDate) .collectLatest {
               _convertLiveData.value=it
           }
        }
    }


    //endregion
}