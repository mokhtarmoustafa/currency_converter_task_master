package com.mokhtar.currencyconverterapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mokhtar.currencyconverterapp.data.remote.repository.CurrencyResponseInterface
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.ui.base.BaseViewModel
import com.mokhtar.currencyconverterapp.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject



interface HomeViewModelInterface{
    val currencyLiveData: LiveData<State<CurrencyResponse>>
    val convertLiveData: LiveData<State<ConvertResponse>>

    fun getCurrencies()
    fun getConvertData( query: String,
                        compact: String,
                        date: String,
                        endDate: String?)
    }

@HiltViewModel
class HomeViewModel @Inject constructor(private val currencyRepository: CurrencyResponseInterface) :HomeViewModelInterface,
    BaseViewModel() {
    //region variables
    private val _currencyLiveData = MutableLiveData<State<CurrencyResponse>>()
    override val currencyLiveData: LiveData<State<CurrencyResponse>>
        get() = _currencyLiveData

    private val _convertLiveData = MutableLiveData<State<ConvertResponse>>()
    override val convertLiveData: LiveData<State<ConvertResponse>>
        get() = _convertLiveData
    //endregion

    //region helper functions

     override fun getCurrencies() {
         viewModelScope.launch {
             currencyRepository.getAllCurrencies() .collectLatest {
                 _currencyLiveData.value = it
             }
         }

    }

     override fun getConvertData(query: String,
                                 compact: String,
                                 date: String,
                                 endDate: String?)
    {
        viewModelScope.launch {

           currencyRepository.getConvertData(query,compact,date,endDate).collectLatest {
               _convertLiveData.value=it
           }
        }
    }


    //endregion
}