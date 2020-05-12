package com.fornaro.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.domain.models.Drink
import br.com.fornaro.drinks.databinding.ItemDrinkBinding

class DrinkAdapter(
    private val action: ((String) -> Unit) = {}
) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    private val list = mutableListOf<Drink>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position], action)

    fun updateData(data: List<Drink>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemDrinkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            drink: Drink,
            action: ((String) -> Unit) = {}
        ) = with(binding) {
            this.drink = drink
            executePendingBindings()
            container.setOnClickListener {  }
        }
    }
}