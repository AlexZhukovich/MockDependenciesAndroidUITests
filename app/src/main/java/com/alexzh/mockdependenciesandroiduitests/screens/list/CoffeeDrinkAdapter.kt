package com.alexzh.mockdependenciesandroiduitests.screens.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexzh.mockdependenciesandroiduitests.R
import com.alexzh.mockdependenciesandroiduitests.screens.list.model.CoffeeDrinkUI
import kotlinx.android.synthetic.main.item_coffee_drink.view.*

class CoffeeDrinkAdapter : RecyclerView.Adapter<CoffeeDrinkAdapter.CoffeeDrinkViewHolder>() {
    private val coffeeDrinks = mutableListOf<CoffeeDrinkUI>()

    fun submitCoffeeDrinks(coffeeDrinks: List<CoffeeDrinkUI>) {
        val diffResult= DiffUtil.calculateDiff(
            CoffeeDrinksDiffCallback(this.coffeeDrinks, coffeeDrinks)
        )
        this.coffeeDrinks.clear()
        this.coffeeDrinks.addAll(coffeeDrinks)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeDrinkViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee_drink, parent, false)
        return CoffeeDrinkViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoffeeDrinkViewHolder, position: Int) {
        holder.bind(coffeeDrinks[position])
    }

    override fun getItemCount(): Int = coffeeDrinks.size

    class CoffeeDrinkViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view) {

        fun bind(coffeeDrink: CoffeeDrinkUI) {
            with(itemView) {
                name.text = coffeeDrink.name
            }
        }
    }
}

class CoffeeDrinksDiffCallback(
    private val oldCoffeeDrinks: List<CoffeeDrinkUI>,
    private val newCoffeeDrinks: List<CoffeeDrinkUI>
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCoffeeDrinks[oldItemPosition].id == newCoffeeDrinks[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldCoffeeDrinks.size

    override fun getNewListSize(): Int = newCoffeeDrinks.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCoffeeDrinks[oldItemPosition] == newCoffeeDrinks[newItemPosition]
    }
}