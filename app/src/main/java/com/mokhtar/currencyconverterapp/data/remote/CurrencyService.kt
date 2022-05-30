package com.mokhtar.currencyconverterapp.data.remote

import com.mokhtar.currencyconverterapp.model.convert.ConvertResponse
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.util.BASE_URL
import com.mokhtar.currencyconverterapp.util.CONVERT_URL
import com.mokhtar.currencyconverterapp.util.CURRENCIES_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to fetch Currencies data
 */
interface CurrencyService {

    @GET(CURRENCIES_URL)
    suspend fun getCurrencies(): Response<CurrencyResponse>



}
interface Ser2 {

    @GET(CONVERT_URL)
    suspend fun getConvertData(
        @Query("q") query: String,
        @Query("compact") compact: String,
        @Query("date") date: String,
        @Query("endDate") endDate: String?
    ): Response<ConvertResponse>

}