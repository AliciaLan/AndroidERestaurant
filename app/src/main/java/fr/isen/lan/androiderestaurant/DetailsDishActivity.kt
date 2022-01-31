package fr.isen.lan.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
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
        binding.dishPrice.text = (dish.prices[0].price + " €")

        var ingredients = ""
        for (i in dish.ingredients) {
            ingredients += (i.name_fr + ", ")
        }
        binding.dishIngredients.text = ingredients

        if (dish.images[0] != "") {
            Picasso.get()
                .load(dish.images[0])
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .fit()
                .into(binding.dishImage)
        } else {
            binding.dishImage.setImageResource(R.drawable.logo)
        }
    }
}