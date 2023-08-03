package com.examples.data.restful

import com.examples.entities.currency.remote.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(Config.LATEST)
    suspend fun getRates(
        @Query("base") base: String
    ): CurrencyResponse
}