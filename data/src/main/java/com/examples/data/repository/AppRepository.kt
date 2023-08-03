package com.examples.data.repository

import com.examples.entities.currency.remote.CurrencyResponse
import retrofit2.http.Query

interface AppRepository {

    suspend fun getRates(
        @Query("base") base: String
    ): CurrencyResponse
}