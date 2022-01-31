package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.lan.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.lan.androiderestaurant.model.DishModel

const val TITLE_DISH = "titleDish"

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryButtonBack.setOnClickListener {
            finish()
        }

        binding.categoryTitle.text = intent.getStringExtra(TITLE_CATEGORY)

        binding.categoryList.layoutManager = LinearLayoutManager(this)

        val dishes = ArrayList<DishModel>()
        for (i in 1..10) {
            dishes.add(DishModel(R.drawable.logo, "Item $i", "desc item $i", "$i"))
        }

        binding.categoryList.adapter = DishAdapter(dishes) {
            val intent = Intent(this, DetailsDishActivity::class.java).apply {
                putExtra(TITLE_DISH, it)
            }
            startActivity(intent)
        }
    }
}
