package fr.isen.lan.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.lan.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.lan.androiderestaurant.model.DishModel

class DishAdapter(private val dishes: List<DishModel>, private val onDishClick : (DishModel) -> Unit) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CategoryCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.imageView.setImageResource(dish.image)
        holder.textView.text = dish.title
        holder.itemView.setOnClickListener {
            onDishClick(dish)
        }
    }

    override fun getItemCount(): Int {
        return dishes.size
    }

    class DishViewHolder(binding : CategoryCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.listImage
        val textView: TextView = binding.listTitle
    }
}
