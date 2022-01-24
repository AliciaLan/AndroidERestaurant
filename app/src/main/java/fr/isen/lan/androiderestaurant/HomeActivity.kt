package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.lan.androiderestaurant.databinding.ActivityHomeBinding
// import android.widget.Toast


const val TITLE_CATEGORY = "title"

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonStarter.setOnClickListener {
            changeActivity(getString(R.string.starter))
        }

        binding.buttonMainDish.setOnClickListener {
            changeActivity(getString(R.string.mainDish))
        }

        binding.buttonDessert.setOnClickListener {
            changeActivity(getString(R.string.dessert))
        }
    }

    private fun changeActivity(category : String) {
        // Toast.makeText(this, category, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra(TITLE_CATEGORY, category)
        }
        Log.d("LOG", "HomeActivity stop")
        startActivity(intent)
    }
}