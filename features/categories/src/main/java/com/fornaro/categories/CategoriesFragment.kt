package com.fornaro.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fornaro.android.fragments.BaseFragment
import br.com.fornaro.categories.R
import br.com.fornaro.domain.models.Category
import kotlinx.android.synthetic.main.fragment_categories.*
import org.koin.android.viewmodel.ext.android.viewModel

class CategoriesFragment : BaseFragment() {

    override val viewModel: CategoriesViewModel by viewModel()

    private val viewAdapter by lazy { CategoryAdapter() }

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

    @Suppress("UNCHECKED_CAST")
    override fun handleData(data: Any?) {
        (data as List<Category>).let {
            viewAdapter.updateData(it)
        }
    }

    override fun handleError(error: Throwable?) {
        error
    }
}