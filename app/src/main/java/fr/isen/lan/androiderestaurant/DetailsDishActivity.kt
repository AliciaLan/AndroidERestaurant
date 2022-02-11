package fr.isen.lan.androiderestaurant

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.lan.androiderestaurant.databinding.ActivityDetailsDishBinding
import fr.isen.lan.androiderestaurant.model.Dish
import fr.isen.lan.androiderestaurant.model.DishBasket
import fr.isen.lan.androiderestaurant.model.ListBasket
import java.io.File

class DetailsDishActivity : MenuActivity() {
    private lateinit var binding : ActivityDetailsDishBinding
    private lateinit var dish : Dish

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dish = intent.getSerializableExtra(getString(R.string.ExtraDishName)) as Dish

        binding.dishTitle.text = dish.name_fr

        val txt = getString(R.string.totalPrice) + dish.prices[0].price + " €"
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
            Snackbar.make(it, getString(R.string.addToBasket), Snackbar.LENGTH_LONG).show()
            updateFile(DishBasket(dish, quantity))
            updateSharedPreferences(quantity, (dish.prices[0].price.toFloat() * quantity))
            finish()
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
                    .placeholder(R.drawable.icon_no_image)
                    .error(R.drawable.icon_no_image)
                    .into(imageView)
            } else {
                Picasso.get()
                    .load(R.drawable.icon_no_image)
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
            dishesBasket = Gson().fromJson(file.readText(), ListBasket::class.java).data
        }

        dishesBasket = dishesBasket + dishBasket
        file.writeText(Gson().toJson(ListBasket(dishesBasket)))
    }

    private fun updateSharedPreferences(quantity: Int, price: Float) {
        val sharedPreferences = this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE)

        val oldQuantity = sharedPreferences.getInt(getString(R.string.spTotalQuantity), 0)
        val newQuantity = oldQuantity + quantity
        sharedPreferences.edit().putInt(getString(R.string.spTotalQuantity), newQuantity).apply()

        val oldPrice = sharedPreferences.getFloat(getString(R.string.spTotalPrice), 0.0f)
        val newPrice = oldPrice + price
        sharedPreferences.edit().putFloat(getString(R.string.spTotalPrice), newPrice).apply()
    }
}