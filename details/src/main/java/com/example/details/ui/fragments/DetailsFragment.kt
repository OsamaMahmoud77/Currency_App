package com.example.details.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.details.databinding.FragmentDetailsBinding
import com.examples.core.base.fragment.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {
    override fun getViewBinding() = FragmentDetailsBinding.inflate(layoutInflater)
    override val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}