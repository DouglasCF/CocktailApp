package com.fornaro.drinkdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.fornaro.android.fragments.StateHandler
import br.com.fornaro.domain.api.NoConnectivityException
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.drinkdetail.R
import br.com.fornaro.drinkdetail.databinding.FragmentDrinkDetailBinding
import kotlinx.android.synthetic.main.fragment_drink_detail.errorView
import kotlinx.android.synthetic.main.fragment_drink_detail.loadingView
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DrinkDetailFragment : Fragment(), StateHandler {

    private val args: DrinkDetailFragmentArgs by navArgs()

    private val viewModel: DrinkDetailViewModel by viewModel {
        parametersOf(args.drinkId)
    }

    private lateinit var binding: FragmentDrinkDetailBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = args.drinkName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrinkDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeState(viewLifecycleOwner, this, ::handleData)
    }

    override fun handleLoading(visible: Boolean) {
        loadingView.isVisible = visible
    }

    private fun handleData(data: Any?) {
        binding.drink = data as Drink
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
