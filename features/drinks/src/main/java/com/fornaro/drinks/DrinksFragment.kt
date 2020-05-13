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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import br.com.fornaro.android.fragments.BaseFragment
import br.com.fornaro.domain.api.NoConnectivityException
import br.com.fornaro.domain.models.DrinksModel
import br.com.fornaro.drinks.R
import kotlinx.android.synthetic.main.fragment_drinks.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DrinksFragment : BaseFragment() {

    override val viewModel: DrinksViewModel by viewModel {
        parametersOf(args.categoryName)
    }

    private val viewAdapter by lazy {
        DrinkAdapter {
            val direction = DrinksFragmentDirections.drinkDetailFragment(it.id, it.name)
            findNavController().navigate(direction)
        }
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
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = GridLayoutManager(context, 2)
        adapter = viewAdapter
    }

    override fun handleLoading(visible: Boolean) {
        loadingView.isVisible = visible
    }

    override fun handleData(data: Any?) {
        (data as DrinksModel).let { viewAdapter.updateData(it.drinkList) }
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
