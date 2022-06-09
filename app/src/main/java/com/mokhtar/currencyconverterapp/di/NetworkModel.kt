package com.mokhtar.currencyconverterapp.di

import com.mokhtar.currencyconverterapp.data.remote.CurrencyService
import com.mokhtar.currencyconverterapp.data.remote.Ser2
import com.mokhtar.currencyconverterapp.data.remote.repository.CurrencyResponseInterface
import com.mokhtar.currencyconverterapp.data.remote.repository.CurrencyRepository
import com.mokhtar.currencyconverterapp.util.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHHtp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor) //for log the response
            .addNetworkInterceptor(ApiInterceptor()) //for modify request to add apikey with each request
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).build()

    }

    @Singleton
    @Provides
    @Named("normal")
    fun provideNormalRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.create()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    @Named("custom")
    fun provideCustomRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonTypeHelper.create()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApiService(@Named("normal") retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }

    @Singleton
    @Provides
    fun provideCustomApiService(@Named("custom") retrofit: Retrofit): Ser2 {
        return retrofit.create(Ser2::class.java)
    }


    @Singleton
    @Provides
    fun bindInterface(repository: CurrencyRepository): CurrencyResponseInterface {
        return repository
    }


}