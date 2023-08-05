package com.example.currency.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.examples.core.base.view_model.BaseViewModel
import com.examples.domain.use_case.CurrencyConverterUseCase
import com.examples.entities.currency.parameters.CurrencyQuery
import com.examples.entities.currency.remote.local.CurrencyBusinessObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class HomeViewModel @Inject constructor(private val currencyConverterUseCase: CurrencyConverterUseCase) :
    BaseViewModel() {

    private val TAG = HomeViewModel::class.simpleName
    private val mutableState = MutableLiveData<CurrencyBusinessObject>()
    val state: LiveData<CurrencyBusinessObject> = mutableState


    fun getCurrencyConverter(param: CurrencyQuery) {
        val fromAmount = param.amountStr.toFloatOrNull() ?: return
        callApi(mutableState) {
            currencyConverterUseCase.execute(param) {
                onComplete {
                    val rates = it.rates
                    val rate = rates?.let { it1 ->
                        currencyConverterUseCase.getRateForCurrency(param.toCurrency,
                            it1
                        )
                    }

                    val convertedCurrency =
                        round(fromAmount * rate!! * 100) / 100
                    val currencyBusinessObject = CurrencyBusinessObject()
                    currencyBusinessObject.resultText = "${param.amountStr} ${param.base} = $convertedCurrency ${param.toCurrency}"
                    mutableState.value = currencyBusinessObject

                }
                onError(::setErrorReason)
                onCancel(::setCancellationReason)
                isLoading(::setLoading)
            }
        }
    }


}