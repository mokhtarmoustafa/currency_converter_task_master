package com.mokhtar.currencyconverterapp.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mokhtar.currencyconverterapp.model.currency.CurrencyResponse
import com.mokhtar.currencyconverterapp.model.currency.Results

object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf(
            migration()
        )

    private fun migration(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${CurrencyResponse.TABLE_NAME} ADD COLUMN body TEXT")
        }
    }
}