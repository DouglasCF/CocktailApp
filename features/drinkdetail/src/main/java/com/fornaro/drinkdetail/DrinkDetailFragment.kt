package com.fornaro.drinkdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import br.com.fornaro.android.fragments.BaseFragment
import br.com.fornaro.domain.api.NoConnectivityException
import br.com.fornaro.domain.models.response.DrinkDetailModel
import br.com.fornaro.drinkdetail.R
import kotlinx.android.synthetic.main.fragment_drink_detail.*
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
        loadingView.isVisible = visible
    }

    override fun handleData(data: Any?) {
        (data as DrinkDetailModel).let { }
    }

    override fun handleError(error: Throwable?) {
        errorView.isVisible = true
        when (error) {
            is NoConnectivityException -> showNoInternetErrorMessage()
            else -> showGenericErrorMessage()
        }
    }

    private fun showNoInternetErrorMessage() {
        // This approach is needed when accessing a layout
        // using include from another module
        errorView.findViewById<ImageView>(R.id.errorImage)
            .setImageResource(R.drawable.ic_signal_wifi_off)

        errorView.findViewById<TextView>(R.id.errorText)
            .text = getString(R.string.no_network_connection_error_message)

        errorView.findViewById<Button>(R.id.errorButton)
            .setOnClickListener {
                errorView.isVisible = false
                viewModel.loadDrink()
            }
    }

    private fun showGenericErrorMessage() {

    }
}
