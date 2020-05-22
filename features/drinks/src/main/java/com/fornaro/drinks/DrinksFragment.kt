package com.fornaro.drinks

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fornaro.android.fragments.StateHandler
import br.com.fornaro.domain.api.NoConnectivityException
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.drinks.R
import kotlinx.android.synthetic.main.fragment_drinks.errorView
import kotlinx.android.synthetic.main.fragment_drinks.loadingView
import kotlinx.android.synthetic.main.fragment_drinks.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DrinksFragment : Fragment(), StateHandler {

    private val viewModel: DrinksViewModel by viewModel {
        parametersOf(args.categoryName)
    }

    private val args: DrinksFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = args.categoryName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.observeState(viewLifecycleOwner, this, ::handleData)
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = GridLayoutManager(context, 2)
        adapter = DrinkAdapter {
            val direction = DrinksFragmentDirections.drinkDetailFragment(it.id, it.name)
            findNavController().navigate(direction)
        }
    }

    override fun handleLoading(visible: Boolean) {
        loadingView.isVisible = visible
    }

    private fun handleData(data: List<Drink>) {
        (recyclerView.adapter as? DrinkAdapter)?.updateData(data)
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
                viewModel.loadDrinks()
            }
    }

    private fun showGenericErrorMessage() {

    }
}
