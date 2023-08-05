package com.examples.domain.mappers.currency

import com.examples.entities.currency.remote.CurrencyResponse
import com.examples.entities.currency.remote.local.CurrencyBusinessObject
import javax.inject.Inject

class CurrencyConverterMapper @Inject constructor() {
    fun convert(dtoItem: CurrencyResponse): CurrencyBusinessObject {
        return CurrencyBusinessObject(
            dtoItem.success ?: false,
            dtoItem.timestamp ?: -1,
            dtoItem.base ?: "",
            dtoItem.date ?: "",
            dtoItem.rates,

        )
    }


}