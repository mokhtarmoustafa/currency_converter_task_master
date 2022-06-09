package com.mokhtar.currencyconverterapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mokhtar.currencyconverterapp.data.remote.repository.CurrencyResponseInterface
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.ui.base.BaseViewModel
import com.mokhtar.currencyconverterapp.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel  @Inject constructor(private val currencyRepository: CurrencyResponseInterface)  : BaseViewModel() {
    //region variables

    private val _convertLiveData = MutableLiveData<State<ConvertResponse>>()
    val convertLiveData: LiveData<State<ConvertResponse>>
        get() = _convertLiveData
    //endregion



    //region helper functions
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