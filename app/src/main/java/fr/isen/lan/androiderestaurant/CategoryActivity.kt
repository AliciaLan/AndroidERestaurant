package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.lan.androiderestaurant.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getStringExtra(TITLE_CATEGORY)
        binding.categoryTitle.text = category

        binding.buttonBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = binding.listCategory
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val data = ArrayList<ItemsViewModel>()
        for (i in 1..10) {
            data.add(ItemsViewModel(R.drawable.logo, "Item $i"))
        }

        recyclerView.adapter = CustomAdapter(data)
    }
}