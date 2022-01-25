package fr.isen.lan.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.lan.androiderestaurant.databinding.ActivityDetailsDishBinding

class DetailsDishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dishButtonBack.setOnClickListener {
            finish()
        }

        val dish : DishViewModel = intent.getSerializableExtra(TITLE_DISH) as DishViewModel
        binding.dishTitle.text = dish.title
        binding.dishDescription.text = dish.description
        binding.dishPrice.text = dish.price
        binding.dishImage.setImageResource(dish.image)
    }
}