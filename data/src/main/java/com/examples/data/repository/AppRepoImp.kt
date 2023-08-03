package com.examples.data.repository

import com.examples.data.source.cloud.BaseCloudRepository
import com.examples.entities.currency.remote.CurrencyResponse
import javax.inject.Inject

class AppRepoImp @Inject constructor(
    private val cloudRepository: BaseCloudRepository
) : AppRepository {
    override suspend fun getRates(base: String): CurrencyResponse {
        return cloudRepository.getRates(base)
    }

}