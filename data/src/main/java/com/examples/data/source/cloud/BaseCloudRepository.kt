package com.examples.data.source.cloud

import com.examples.entities.currency.remote.CurrencyResponse
import retrofit2.http.Query

interface BaseCloudRepository {
    suspend fun getRates(
        @Query("base") base: String
    ): CurrencyResponse
}