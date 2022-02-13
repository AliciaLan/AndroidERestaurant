package fr.isen.lan.androiderestaurant.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lan.androiderestaurant.databinding.BasketCellBinding
import fr.isen.lan.androiderestaurant.model.DishBasket

/**
 * Adapter of [fr.isen.lan.androiderestaurant.BasketActivity] RecyclerView.
 * @param baskets list of dishes to display in the basket.
 * @param onBasketClick function executed on click on delete icon. Parameter [DishBasket] : dish to delete from the basket.
 * @return RecyclerView.Adapter.
 */
class BasketAdapter(private val baskets : List<DishBasket>, private val onBasketClick : (DishBasket) -> Unit) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            BasketCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket = baskets[position]

        holder.name.text = basket.dish.name_fr

        val price = "Total : " + basket.dish.prices[0].price.toFloat() * basket.quantity + " €"
        holder.price.text = price

        val quantity = "Quantité : " + basket.quantity
        holder.quantity.text = quantity

        holder.delete.setOnClickListener {
            onBasketClick(basket)
        }
    }

    override fun getItemCount(): Int {
        return baskets.size
    }

    class BasketViewHolder(binding : BasketCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.basketCellTitle
        val price: TextView = binding.basketCellPrice
        val quantity: TextView = binding.basketCellQuantity
        val delete: ImageView = binding.basketCellIconDelete
    }
}