package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.lan.androiderestaurant.databinding.ActivityHomeBinding

const val TITLE_CATEGORY = "title"

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeButtonStarter.setOnClickListener {
            changeActivity(getString(R.string.starter))
        }

        binding.homeButtonMainDish.setOnClickListener {
            changeActivity(getString(R.string.mainDish))
        }

        binding.homeButtonDessert.setOnClickListener {
            changeActivity(getString(R.string.dessert))
        }
    }

    private fun changeActivity(category : String) {
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra(TITLE_CATEGORY, category)
        }
        startActivity(intent)
    }
}