package fr.isen.lan.androiderestaurant

import android.content.Intent
import android.os.Bundle
import fr.isen.lan.androiderestaurant.databinding.ActivityHomeBinding

/**
 * Main activity of the application (Launcher).
 */
class HomeActivity : MenuActivity() {
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

    /**
     * Start [CategoryActivity] and give it a category.
     * @param category category chosen by the user.
     */
    private fun changeActivity(category : String) {
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra(getString(R.string.ExtraCategoryTitle), category)
        }
        startActivity(intent)
    }
}