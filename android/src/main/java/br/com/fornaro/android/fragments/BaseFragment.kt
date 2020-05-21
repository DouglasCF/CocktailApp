package br.com.fornaro.android.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.fornaro.android.viewmodel.BaseViewModel
import br.com.fornaro.android.viewmodel.State

abstract class BaseFragment<T> : Fragment() {

    abstract val viewModel: BaseViewModel

    abstract fun handleLoading(visible: Boolean)
    abstract fun handleData(data: T?)
    abstract fun handleError(error: Throwable?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleViewModelState()
    }

    private fun handleViewModelState() = with(viewModel) {
        state.observe(viewLifecycleOwner, Observer { handleState(it) })
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleState(state: State) {
        handleLoading(false)
        when {
            state.isSuccess() -> handleData(state.getDataOrNull() as T)
            state.isError() -> handleError(state.getErrorOrNull())
            state.isLoading() -> handleLoading(true)
        }
    }
}