package com.example.currency.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.currency.databinding.FragmentHomeBinding
import com.examples.core.base.fragment.BaseFragment
import com.examples.entities.currency.parameters.CurrencyQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    override val viewModel by viewModels<HomeViewModel>()

    private val TAG = HomeFragment::class.java.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun convert(
        amountStr: String,
        fromCurrency: CurrencyQuery,
        toCurrency: String
    ) {
    }

    }