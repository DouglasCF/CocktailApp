package com.fornaro.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.android.fragments.StateHandler
import br.com.fornaro.categories.R
import br.com.fornaro.domain.api.NoConnectivityException
import kotlinx.android.synthetic.main.fragment_categories.errorView
import kotlinx.android.synthetic.main.fragment_categories.loadingView
import kotlinx.android.synthetic.main.fragment_categories.recyclerView
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesFragment : Fragment(), StateHandler {

    private val viewModel: CategoriesViewModel by viewModel()

    private val viewAdapter by lazy {
        CategoryAdapter {
            val direction = CategoriesFragmentDirections.drinksFragment(it)
            findNavController().navigate(direction)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.observeState(viewLifecycleOwner, this, ::handleData)
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = viewAdapter
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun handleLoading(visible: Boolean) {
        loadingView.isVisible = visible
    }

    private fun handleData(data: List<String>) {
        viewAdapter.updateData(data)
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
                viewModel.loadCategories()
            }
    }

    private fun showGenericErrorMessage() {

    }
}