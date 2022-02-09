package fr.isen.lan.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lan.androiderestaurant.databinding.BasketCellBinding
import fr.isen.lan.androiderestaurant.model.DishBasket

class BasketAdapter(private val baskets : List<DishBasket>) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = BasketCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket = baskets[position]

        holder.name.text = basket.dish.name_fr
        val price = "Total : ${basket.dish.prices[0].price.toFloat() * basket.quantity} €"
        holder.price.text = price
        val quantity = "Quantité : ${basket.quantity}"
        holder.quantity.text = quantity
    }

    override fun getItemCount(): Int {
        return baskets.size
    }

    class BasketViewHolder(binding : BasketCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.basketCellTitle
        val price: TextView = binding.basketCellPrice
        val quantity: TextView = binding.basketCellQuantity
    }
}