package fr.isen.lan.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.lan.androiderestaurant.adapter.BasketAdapter
import fr.isen.lan.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.lan.androiderestaurant.model.DishBasket
import fr.isen.lan.androiderestaurant.model.ListBasket
import java.io.File

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

        binding.basketButtonBuy.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun display(dishesList : List<DishBasket>) {
        binding.basketList.layoutManager = LinearLayoutManager(this)
        binding.basketList.adapter = BasketAdapter(dishesList)
    }
}