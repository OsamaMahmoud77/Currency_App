package com.examples.core.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examples.domain.base.CompletionBlock
import com.examples.entities.response.ErrorModel
import com.examples.entities.response.ErrorStatus
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException
import javax.inject.Inject


open class BaseViewModel @Inject constructor() : ViewModel() {
    private val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isLoadingLiveData: MutableLiveData<Boolean> by lazy { isLoading }

    private val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }
    val errorLiveData: LiveData<ErrorModel> by lazy { error }

    private val cancellationMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val cancellationMsgLiveData: LiveData<String> by lazy { cancellationMessage }

    fun setLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    fun setErrorReason(errorModel: ErrorModel) {
        error.value = errorModel
    }

    fun setCancellationReason(cancellationException: CancellationException) {
        cancellationMessage.value = cancellationException.message
    }

    fun <T> callApi(data: MutableLiveData<T>, apiCall: (CompletionBlock<T>) -> Unit) {
        apiCall.invoke {
            isLoading {
                isLoading.value = it
            }
            onComplete {
                data.value = it
            }
            onError { throwable ->
                when (throwable.errorStatus) {
                    ErrorStatus.UNAUTHORIZED -> {
                    }
                    else -> error.value = throwable
                }

            }
            onCancel {
                cancellationMessage.value = it.message
            }
        }
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    fun <T> callApi(data: ConflatedBroadcastChannel<T>, apiCall: (CompletionBlock<T>) -> Unit) {
        apiCall.invoke {
            isLoading {
                isLoading.value = it
            }
            onComplete {
                viewModelScope.launch {
                    data.trySend(it).isSuccess
                }
            }
            onError { throwable ->
                when (throwable.errorStatus) {
                    ErrorStatus.UNAUTHORIZED -> {
                    }
                    else -> error.value = throwable
                }

            }
            onCancel {
                cancellationMessage.value = it.message
            }
        }
    }
}