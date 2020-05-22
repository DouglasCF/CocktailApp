package br.com.fornaro.android.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import br.com.fornaro.android.fragments.StateHandler

open class BaseViewModel<T> : ViewModel() {

    protected val state = MutableLiveData<State<T>>()

    fun observeState(
        lifecycleOwner: LifecycleOwner,
        handler: StateHandler,
        successHandler: (T) -> Unit
    ) {
        state.observe(lifecycleOwner, Observer { state ->
            handler.handleLoading(false)
            when (state) {
                is State.Success -> state.data?.let(successHandler)
                is State.Error -> handler.handleError(state.error)
                is State.Loading -> handler.handleLoading(true)
            }
        })
    }
}

sealed class State<T>(val data: T? = null, val error: Throwable? = null) {
    class Success<T>(data: T? = null) : State<T>(data = data)
    class Error<T>(error: Throwable) : State<T>(error = error)
    class Loading<T>() : State<T>()

    companion object {
        suspend fun <T> runBlocking(block: suspend () -> T): State<T> {
            return try {
                Success(block())
            } catch (e: Throwable) {
                Error(e)
            }
        }

        fun <T> runCatching(block: () -> T): State<T> {
            return try {
                Success(block())
            } catch (e: Throwable) {
                Error(e)
            }
        }
    }
}