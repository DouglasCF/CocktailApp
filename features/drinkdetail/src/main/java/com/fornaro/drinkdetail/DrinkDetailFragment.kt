package com.fornaro.drinkdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import br.com.fornaro.android.fragments.BaseFragment
import br.com.fornaro.drinkdetail.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DrinkDetailFragment : BaseFragment() {

    private val args: DrinkDetailFragmentArgs by navArgs()

    override val viewModel: DrinkDetailViewModel by viewModel {
        parametersOf(args.drinkId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_drink_detail, container, false)
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
