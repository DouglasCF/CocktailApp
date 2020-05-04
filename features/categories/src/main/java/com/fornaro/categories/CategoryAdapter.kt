package com.fornaro.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.categories.databinding.ItemCategoryBinding
import br.com.fornaro.domain.models.Category

class CategoryAdapter(
    private val action: ((String) -> Unit) = {}
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val list = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position], action)

    fun updateData(data: List<Category>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            category: Category,
            action: ((String) -> Unit) = {}
        ) = with(binding) {
            this.category = category
            executePendingBindings()
            container.setOnClickListener { action.invoke(category.name) }
        }
    }
}
