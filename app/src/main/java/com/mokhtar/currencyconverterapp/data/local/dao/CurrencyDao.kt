package com.mokhtar.currencyconverterapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.model.currency.Results
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrencies(currencies: CurrencyResponse)

    @Query("SELECT * FROM TBL_Currencies ORDER BY id ASC")
    fun getAllCurrencies(): Flow<CurrencyResponse>

    
}