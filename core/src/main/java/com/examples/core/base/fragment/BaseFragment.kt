package com.examples.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.examples.core.base.view_model.BaseViewModel
import com.examples.core.utils.LoadingListener
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    private val TAG = BaseFragment::class.java.simpleName

    private var mLoader: LoadingListener? = null
    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    abstract fun getViewBinding(): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            errorLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(context, "${it?.message}", Toast.LENGTH_LONG).show()
            }

            cancellationMsgLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }

            isLoadingLiveData.observe(viewLifecycleOwner) {
                showLoading(it)
                Log.d(TAG, "Loading observer is called")
            }
        }
    }

    open fun showLoading(show: Boolean) {
        mLoader?.showLoading(show)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            if (context is LoadingListener)
                mLoader = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mLoader = null
    }
}
