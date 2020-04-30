package br.com.fornaro.android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state
}

sealed class State {
    data class Success<T>(val data: T? = null) : State()
    data class Error(val error: Throwable) : State()
    object Loading : State()

    fun isSuccess() = this is Success<*>
    fun isError() = this is Error
    fun isLoading() = this is Loading

    fun getDataOrNull() = when {
        isSuccess() -> (this as Success<*>).data
        else -> null
    }

    fun getErrorOrNull() = when {
        isError() -> (this as Error).error
        else -> null
    }

    companion object {
        suspend fun <T> runBlocking(block: suspend () -> T): State {
            return try {
                Success(block())
            } catch (e: Throwable) {
                Error(e)
            }
        }

        fun <T> runCatching(block: () -> T): State {
            return try {
                Success(block())
            } catch (e: Throwable) {
                Error(e)
            }
        }
    }
}