package com.example.currency.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.examples.core.base.view_model.BaseViewModel
import com.examples.domain.use_case.CurrencyConverterUseCase
import com.examples.entities.currency.parameters.CurrencyQuery
import com.examples.entities.currency.remote.local.CurrencyBusinessObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val currencyConverterUseCase: CurrencyConverterUseCase) :
    BaseViewModel() {
    private val TAG = HomeViewModel::class.simpleName
    private val mutableState = MutableLiveData<CurrencyBusinessObject>()
     val state: LiveData<CurrencyBusinessObject> = mutableState


    fun getCurrencyConverter(param: CurrencyQuery) {
        callApi(mutableState) { statesCallBack ->
            currencyConverterUseCase.execute(param, statesCallBack)
        }
    }

}