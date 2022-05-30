package com.mokhtar.currencyconverterapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mokhtar.currencyconverterapp.data.local.dao.CurrencyDao
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.model.currency.Results
import com.mokhtar.currencyconverterapp.util.CurrencyConverter
import com.mokhtar.currencyconverterapp.util.DB_NAME


@Database(
    entities = [CurrencyResponse::class],
    version = DatabaseMigrations.DB_VERSION,
    exportSchema = false
)

@TypeConverters(CurrencyConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun getCurrenciesDao(): CurrencyDao

    companion object {


        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getInstance(context: Context): CurrencyDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    DB_NAME
                )
                    .addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }
}