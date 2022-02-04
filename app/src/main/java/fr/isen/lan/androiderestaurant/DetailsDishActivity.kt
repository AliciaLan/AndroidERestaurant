package fr.isen.lan.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.lan.androiderestaurant.databinding.ActivityDetailsDishBinding
import fr.isen.lan.androiderestaurant.model.Dish
import fr.isen.lan.androiderestaurant.model.DishBasket
import java.io.File

class DetailsDishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsDishBinding
    private lateinit var dish : Dish

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dish = intent.getSerializableExtra(DISH) as Dish
        binding.dishTitle.text = dish.name_fr
        val txt = "Total : ${dish.prices[0].price} €"
        binding.dishPriceButton.text = txt

        var quantity = 1
        binding.dishQuantity.text = quantity.toString()

        binding.dishLessButton.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantityPrice(quantity)
            }
        }

        binding.dishMoreButton.setOnClickListener {
            quantity++
            updateQuantityPrice(quantity)
        }


        binding.dishPriceButton.setOnClickListener {
            Snackbar.make(it, "Ajout au panier", Snackbar.LENGTH_LONG).show()
            updateFile(DishBasket(dish, quantity))
        }

        var ingredients = ""
        for (i in dish.ingredients) {
            ingredients += (i.name_fr + ", ")
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
                    .into(imageView)
            } else {
                Picasso.get()
                    .load(R.drawable.logo)
                    .into(imageView)
            }
        }
    }

    private fun updateQuantityPrice(quantity : Int) {
        binding.dishQuantity.text = quantity.toString()
        val price = dish.prices[0].price.toFloat()
        val totalPrice = "Total : ${price * quantity} €"
        binding.dishPriceButton.text = totalPrice
    }

    private fun updateFile(dishBasket : DishBasket) {
        val file = File(cacheDir.absolutePath + "/basket.json")
        var dishesBasket: List<DishBasket> = ArrayList()

        if (file.exists()) {
            dishesBasket = Gson().fromJson(file.readText(), List::class.java) as List<DishBasket>
        }

        dishesBasket = dishesBasket + dishBasket
        file.writeText(Gson().toJson(dishesBasket))
    }
}