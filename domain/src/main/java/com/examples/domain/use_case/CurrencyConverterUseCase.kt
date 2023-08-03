package com.examples.domain.use_case

import com.examples.data.mapper.CloudErrorMapper
import com.examples.data.repository.AppRepository
import com.examples.domain.base.RemoteUseCase
import com.examples.domain.mappers.currency.CurrencyConverterMapper
import com.examples.entities.currency.parameters.CurrencyQuery
import com.examples.entities.currency.remote.CurrencyResponse
import com.examples.entities.currency.remote.local.CurrencyBusinessObject
import javax.inject.Inject

class CurrencyConverterUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository,
    private val mapper: CurrencyConverterMapper
) : RemoteUseCase<CurrencyQuery, CurrencyResponse, CurrencyBusinessObject>(errorUtil) {
    override suspend fun convert(dto: CurrencyResponse): CurrencyBusinessObject {
        return mapper.convert(dto)
    }

    override suspend fun executeOnBackground(parameters: CurrencyQuery): CurrencyResponse {
        return appRepository.getRates(parameters.base)
    }
}