package com.mokhtar.currencyconverterapp.data.remote.repository

import android.content.Context
import com.mokhtar.currencyconverterapp.data.local.dao.CurrencyDao
import com.mokhtar.currencyconverterapp.data.remote.CurrencyService
import com.mokhtar.currencyconverterapp.data.remote.Ser2
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.country.CountryResponse
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.model.currency.Results
import com.mokhtar.currencyconverterapp.util.*
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

@ActivityRetainedScoped
class CurrencyRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val currencyDao: CurrencyDao,
    private val currencyService: CurrencyService,
    private val currencySer2: Ser2
) {

    fun getAllCurrencies(): Flow<State<CurrencyResponse>> {

        return object : NetworkBoundRepository<CurrencyResponse, CurrencyResponse>() {

            override suspend fun saveRemoteData(response: CurrencyResponse) =
                currencyDao.insertCurrencies(response)

            override fun fetchFromLocal(): Flow<CurrencyResponse> = currencyDao.getAllCurrencies()

            override suspend fun fetchFromRemote(): Response<CurrencyResponse> =
                currencyService.getCurrencies()

        }.asFlow().flowOn(Dispatchers.IO)
    }


    suspend fun getConvertData(
        query: String,
        compact: String,
        date: String,
        endDate: String?
    ): Flow<State<ConvertResponse>> = flow {
        val convertData = currencySer2.getConvertData(query, compact, date, endDate)
        if (convertData.isSuccessful && convertData.body() != null) {
            convertData.body().let {
                emit(State.Success(it!!))
            }
        }
    }.flowOn(Dispatchers.IO)
}
