package com.fornaro.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.android.fragments.BaseFragment
import br.com.fornaro.categories.R
import br.com.fornaro.domain.api.NoConnectivityException
import br.com.fornaro.domain.models.CategoriesModel
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesFragment : BaseFragment() {

    override val viewModel: CategoriesViewModel by viewModel()

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
    }

    private fun setupRecyclerView() = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = viewAdapter
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun handleLoading(visible: Boolean) {
        loadingView.isVisible = visible
    }

    override fun handleData(data: Any?) {
        (data as CategoriesModel).let { viewAdapter.updateData(it.categoryList) }
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