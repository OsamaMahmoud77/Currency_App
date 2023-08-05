package com.examples.domain.use_case

import com.examples.data.mapper.CloudErrorMapper
import com.examples.data.repository.AppRepository
import com.examples.domain.base.RemoteUseCase
import com.examples.domain.mappers.currency.CurrencyConverterMapper
import com.examples.entities.currency.parameters.CurrencyQuery
import com.examples.entities.currency.remote.CurrencyResponse
import com.examples.entities.currency.remote.Rates
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

    fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
        "CAD" -> rates.cad
        "HKD" -> rates.hkd
        "ISK" -> rates.isk
        "EUR" -> rates.eur
        "PHP" -> rates.php
        "DKK" -> rates.dkk
        "HUF" -> rates.huf
        "CZK" -> rates.czk
        "AUD" -> rates.aud
        "RON" -> rates.ron
        "SEK" -> rates.sek
        "IDR" -> rates.idr
        "INR" -> rates.inr
        "BRL" -> rates.brl
        "RUB" -> rates.rub
        "HRK" -> rates.hrk
        "JPY" -> rates.jpy
        "THB" -> rates.thb
        "CHF" -> rates.chf
        "SGD" -> rates.sgd
        "PLN" -> rates.pln
        "BGN" -> rates.bgn
        "CNY" -> rates.cny
        "NOK" -> rates.nok
        "NZD" -> rates.nzd
        "ZAR" -> rates.zar
        "USD" -> rates.usd
        "MXN" -> rates.mxn
        "ILS" -> rates.ils
        "GBP" -> rates.gbp
        "KRW" -> rates.krw
        "MYR" -> rates.myr
        else -> null
    }

}