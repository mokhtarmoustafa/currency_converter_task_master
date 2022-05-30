package com.example.recipeium.di.remote

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.mokhtar.currencyconverterapp.data.local.CurrencyDatabase
import com.mokhtar.currencyconverterapp.data.local.dao.CurrencyDao
import com.mokhtar.currencyconverterapp.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDatabaseModel {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        CurrencyDatabase::class.java,
        DB_NAME
    ).build()


    @Provides
    @Singleton
    fun provideDao(roomDatabase: CurrencyDatabase): CurrencyDao {
        return roomDatabase.getCurrenciesDao()
    }
}