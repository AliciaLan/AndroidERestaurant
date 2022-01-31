package fr.isen.lan.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.lan.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.lan.androiderestaurant.model.Dish
import org.w3c.dom.Text

class DishAdapter(private val dishes: List<Dish>, private val onDishClick : (Dish) -> Unit) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CategoryCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.name.text = dish.name_fr
        holder.price.text = dish.prices[0].price

        if (dish.images[0] != "") {
            Picasso.get()
                .load(dish.images[0])
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .fit()
                .into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.logo)
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
