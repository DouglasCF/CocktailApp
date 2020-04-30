package com.fornaro.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fornaro.android.fragments.BaseFragment
import br.com.fornaro.categories.R
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesFragment : BaseFragment() {

    override val viewModel: CategoriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.categories_fragment, container, false)
    }

    override fun handleLoading(visible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun handleData(data: Any?) {
        TODO("Not yet implemented")
    }

    override fun handleError(error: Throwable?) {
        TODO("Not yet implemented")
    }
}