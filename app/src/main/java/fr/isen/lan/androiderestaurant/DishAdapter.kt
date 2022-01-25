package fr.isen.lan.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DishAdapter(private val mList: List<DishViewModel>, private val cellClickListener: CellClickListener) : RecyclerView.Adapter<DishAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dishesViewModel = mList[position]
        holder.imageView.setImageResource(dishesViewModel.image)
        holder.textView.text = dishesViewModel.title
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(dishesViewModel)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.listImage)
        val textView: TextView = itemView.findViewById(R.id.listTitle)
    }
}
