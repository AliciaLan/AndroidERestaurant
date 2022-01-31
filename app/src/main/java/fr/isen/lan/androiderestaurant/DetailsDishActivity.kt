package fr.isen.lan.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.lan.androiderestaurant.databinding.ActivityDetailsDishBinding
import fr.isen.lan.androiderestaurant.model.Dish

class DetailsDishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dishButtonBack.setOnClickListener {
            finish()
        }

        val dish : Dish = intent.getSerializableExtra(DISH) as Dish
        binding.dishTitle.text = dish.name_fr
        //binding.dishDescription.text = dish.description
        binding.dishPrice.text = dish.prices[0].price
        //binding.dishImage.setImageResource(dish.image)
    }
}