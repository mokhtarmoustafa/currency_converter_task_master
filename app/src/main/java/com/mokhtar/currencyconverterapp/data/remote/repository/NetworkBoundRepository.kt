package com.mokhtar.currencyconverterapp.data.remote.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.mokhtar.currencyconverterapp.util.ResponseType
import com.mokhtar.currencyconverterapp.util.State
import kotlinx.coroutines.flow.*
import retrofit2.Response


abstract class NetworkBoundRepository<RESULT, REQUEST> {
    fun asFlow() = flow {

        // Emit Loading State
        emit(State.loading())


        try {

            // Emit Database content first
            emit(State.success(fetchFromLocal().first()))

            // Fetch latest currency from remote
            val apiResponse = fetchFromRemote()

            // Parse body
            val remoteCurrencies = apiResponse.body()

            // Check for response validation
            if (apiResponse.isSuccessful && remoteCurrencies != null) {
                // Save currencies into the persistence storage
                saveRemoteData(remoteCurrencies)
            } else {

                    emit(State.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(State.error("Network error! Can't get latest Data."))
            e.printStackTrace()
        }

        // Retrieve Currencies from persistence storage and emit
        emitAll(fetchFromLocal().map {
            State.success<RESULT>(it)
        })
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
     abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
     abstract fun fetchFromLocal(): Flow<RESULT>

//    @MainThread
//     abstract fun fetchFromAssets(): Flow<RESULT>
    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}