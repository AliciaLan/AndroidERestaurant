package fr.isen.lan.androiderestaurant.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.lan.androiderestaurant.R
import fr.isen.lan.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.lan.androiderestaurant.model.Dish

class DishAdapter(private val dishes: List<Dish>, private val onDishClick : (Dish) -> Unit) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CategoryCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.name.text = dish.name_fr

        val txt = "${dish.prices[0].price} €"
        holder.price.text = txt

        if (dish.images[0].isNotEmpty()) {
            Picasso.get()
                .load(dish.images[0])
                .placeholder(R.drawable.icon_no_image)
                .error(R.drawable.icon_no_image)
                .into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.icon_no_image)
        }

        holder.itemView.setOnClickListener {
            onDishClick(dish)
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    class DishViewHolder(binding : CategoryCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.listTitle
        val price: TextView = binding.listPrice
        val image: ImageView = binding.listImage
    }
}