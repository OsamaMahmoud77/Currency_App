package com.example.currency.ui.fragment.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.currency.databinding.FragmentHomeBinding
import com.examples.core.base.fragment.BaseFragment
import com.examples.core.utils.NetworkingUtils
import com.examples.entities.currency.parameters.CurrencyQuery
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    override val viewModel by viewModels<HomeViewModel>()

    private val TAG = HomeFragment::class.java.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConvert.setOnClickListener {
            convert(
                CurrencyQuery().apply {
                    amountStr = binding.etFrom.text.toString()
                    base = binding.spFromCurrency.selectedItem.toString()
                    toCurrency = binding.spToCurrency.selectedItem.toString()
                })
        }

    }


    private fun convert(param: CurrencyQuery) {
        lifecycleScope.launch {
            with(viewModel) {
                if (NetworkingUtils.isNetworkConnected)
                    getCurrencyConverter(param)

            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            binding.tvResult.setTextColor(Color.BLACK)
            binding.tvResult.text = it.resultText
        }

    }


}