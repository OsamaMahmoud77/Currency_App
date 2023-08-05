package com.examples.entities.currency.remote.local

import com.examples.entities.currency.remote.Rates

data class CurrencyBusinessObject(
    val success: Boolean = false,
    val timestamp: Int = -1,
    var base: String = "",
    val date: String = "",
    val rates: Rates? = Rates(),
    var resultText: String = ""
    )