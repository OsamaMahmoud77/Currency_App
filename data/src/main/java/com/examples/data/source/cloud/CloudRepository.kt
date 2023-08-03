package com.examples.data.source.cloud

import com.examples.data.restful.ApiService
import com.examples.entities.currency.remote.CurrencyResponse

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun getRates(base: String): CurrencyResponse {
        return apIs.getRates(base)
    }

}
