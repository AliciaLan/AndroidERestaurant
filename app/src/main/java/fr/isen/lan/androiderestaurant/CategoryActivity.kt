package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.lan.androiderestaurant.databinding.ActivityCategoryBinding

const val TITLE_DISH = "titleDish"

class CategoryActivity : AppCompatActivity(), CellClickListener {
    private lateinit var binding : ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra(TITLE_CATEGORY)
        binding.categoryTitle.text = category

        binding.buttonBackCategory.setOnClickListener {
            finish()
        }

        val recyclerView = binding.listCategory
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val data = ArrayList<DishViewModel>()
        for (i in 1..10) {
            data.add(DishViewModel(R.drawable.logo, "Item $i"))
        }

        recyclerView.adapter = CustomAdapter(data, this)
    }

    override fun onCellClickListener(dish : DishViewModel) {
        val intent = Intent(this, DetailsDishActivity::class.java).apply {
            putExtra(TITLE_DISH, dish.text)
        }
        startActivity(intent)
    }
}