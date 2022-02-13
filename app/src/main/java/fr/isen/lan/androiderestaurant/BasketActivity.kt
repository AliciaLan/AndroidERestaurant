package fr.isen.lan.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.lan.androiderestaurant.adapter.BasketAdapter
import fr.isen.lan.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.lan.androiderestaurant.model.DishBasket
import fr.isen.lan.androiderestaurant.model.ListBasket
import java.io.File

/**
 * Display the basket.
 * Use [BasketAdapter] for RecyclerView.
 */
class BasketActivity : MenuActivity() {
    private lateinit var binding : ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val file = File(cacheDir.absolutePath + "/basket.json")

        if (file.exists()) {
            val dishesBasket : List<DishBasket> = Gson().fromJson(file.readText(), ListBasket::class.java).data
            display(dishesBasket)
        }

        val quantity = getString(R.string.basketTotalQuantity) + this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getInt(getString(R.string.spTotalQuantity), 0).toString()
        binding.basketTotalQuantity.text = quantity

        val price = getString(R.string.totalPrice) + this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getFloat(getString(R.string.spTotalPrice), 0.0f).toString()
        binding.basketTotalPrice.text = price

        binding.basketButtonBuy.setOnClickListener {
            val userId = this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).getInt(getString(R.string.spUserId), 0)

            if (userId == 0) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }

        binding.basketButtonDeleteAll.setOnClickListener {
            deleteBasketData()
        }
    }

    /**
     * Display the list of dishes in the recyclerView thanks to [BasketAdapter].
     * @param dishesList list to display.
     */
    private fun display(dishesList : List<DishBasket>) {
        binding.basketList.layoutManager = LinearLayoutManager(this)
        binding.basketList.adapter = BasketAdapter(dishesList) {
            deleteDishBasket(it)
        }
    }

    /**
     * Delete a dish from the basket list.
     * Update shared preferences and basket file.
     * @param dish dish to delete.
     */
    private fun deleteDishBasket(dish : DishBasket) {
        val file = File(cacheDir.absolutePath + "/basket.json")
        var dishesBasket: List<DishBasket> = ArrayList()

        if (file.exists()) {
            dishesBasket = Gson().fromJson(file.readText(), ListBasket::class.java).data
            dishesBasket = dishesBasket - dish
            updateSharedPreferences(dish.quantity, dish.dish.prices[0].price.toFloat())
        }

        file.writeText(Gson().toJson(ListBasket(dishesBasket)))

        finish()
        this.recreate()
    }

    /**
     * Delete all data in the basket list.
     * Delete shared preferences (quantity and price) and basket file.
     */
    private fun deleteBasketData() {
        File(cacheDir.absolutePath + "/basket.json").delete()
        this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).edit().remove(getString(R.string.spTotalPrice)).apply()
        this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).edit().remove(getString(R.string.spTotalQuantity)).apply()
        Toast.makeText(this, getString(R.string.basketDeleteAllTxt), Toast.LENGTH_SHORT).show()
    }

    /**
     * Update shared preferences with new quantity and price.
     * @param quantity quantity of the deleted item.
     * @param price price of the deleted item.
     */
    private fun updateSharedPreferences(quantity: Int, price: Float) {
        val sharedPreferences = this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE)

        val oldQuantity = sharedPreferences.getInt(getString(R.string.spTotalQuantity), 0)
        val newQuantity = oldQuantity - quantity
        sharedPreferences.edit().putInt(getString(R.string.spTotalQuantity), newQuantity).apply()

        val oldPrice = sharedPreferences.getFloat(getString(R.string.spTotalPrice), 0.0f)
        val newPrice = oldPrice - price
        sharedPreferences.edit().putFloat(getString(R.string.spTotalPrice), newPrice).apply()
    }
}