package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.lan.androiderestaurant.databinding.ActivityDetailsDishBinding

class DetailsDishActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBackDetails.setOnClickListener {
            finish()
        }

        val dish = intent.getStringExtra(TITLE_DISH)
        binding.dishTitle.text = dish
    }
}