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
        val txt = "${dish.prices[0].price} €"
        binding.dishPrice.text = txt

        var quantity = 1
        binding.dishQuantity.text = quantity.toString()
        val price = dish.prices[0].price.toFloat()

        binding.dishLessButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                binding.dishQuantity.text = quantity.toString()
                val totalPrice = "${price * quantity} €"
                binding.dishPrice.text = totalPrice
            }
        }

        binding.dishMoreButton.setOnClickListener {
            quantity++
            binding.dishQuantity.text = quantity.toString()
            val totalPrice = "${price * quantity} €"
            binding.dishPrice.text = totalPrice
        }

        var ingredients = ""
        for (i in dish.ingredients) {
            ingredients += (i.name_fr + "\n")
        }
        binding.dishIngredients.text = ingredients

        val carouselView = binding.dishImage
        carouselView.pageCount = dish.images.size
        carouselView.setImageListener { position, imageView ->
            if (dish.images[0].isNotEmpty()) {
                Picasso.get()
                    .load(dish.images[position])
                    .placeholder(R.drawable.logo)
                    .error(R.drawable.logo)
                    .fit()
                    .into(imageView)
            } else {
                Picasso.get()
                    .load(R.drawable.logo)
                    .into(imageView)
            }
        }
    }
}