package com.mokhtar.currencyconverterapp.data.remote.repository

import com.mokhtar.currencyconverterapp.data.local.dao.CurrencyDao
import com.mokhtar.currencyconverterapp.data.remote.CurrencyService
import com.mokhtar.currencyconverterapp.data.remote.Ser2
import com.mokhtar.currencyconverterapp.model.convert.ConvertData
import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.convert.ValueData
import com.mokhtar.currencyconverterapp.model.currency.Currency
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.util.*
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject


class MockCurrencyRepo:CurrencyResponseInterface{

    val currencyResponse = CurrencyResponse(mutableListOf(Currency("ABC","BBB","IDID")))

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

       val y = ConvertResponse(mutableListOf(ConvertData("A","B","T", mutableListOf(ValueData(231313.0)),false)))
        return flow{
            emit(State.success(y))
        }

    }


}

@InstallIn(SingletonComponent::class)
@Module
class CurrencyRepository @Inject  constructor(
    private val currencyDao: CurrencyDao,
    private val currencyService: CurrencyService,
    private val ser2: Ser2,
):CurrencyResponseInterface {

    override fun getAllCurrencies(): Flow<State<CurrencyResponse>> {

        return object : NetworkBoundRepository<CurrencyResponse, CurrencyResponse>() {

            override suspend fun saveRemoteData(response: CurrencyResponse) =
                currencyDao.insertCurrencies(response)

            override fun fetchFromLocal(): Flow<CurrencyResponse> = currencyDao.getAllCurrencies()

            override suspend fun fetchFromRemote(): Response<CurrencyResponse> =
                currencyService.getCurrencies()

        }.asFlow().flowOn(Dispatchers.IO)
    }

    override suspend fun getConvertData(
        query: String,
        compact: String,
        date: String,
        endDate: String?
    ): Flow<State<ConvertResponse>> = flow {
        val convertData = ser2.getConvertData(query, compact, date, endDate)
        if (convertData.isSuccessful && convertData.body() != null) {
            convertData.body().let {
                emit(State.Success(it!!))
            }
        }
    }.flowOn(Dispatchers.IO)


}
