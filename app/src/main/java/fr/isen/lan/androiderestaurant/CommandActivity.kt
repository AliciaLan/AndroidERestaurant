package fr.isen.lan.androiderestaurant

import android.os.Bundle
import fr.isen.lan.androiderestaurant.databinding.ActivityCommandBinding

class CommandActivity : MenuActivity() {
    private lateinit var binding : ActivityCommandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommandBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}