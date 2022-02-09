package fr.isen.lan.androiderestaurant

import android.os.Bundle

class CommandActivity : MenuActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command)
    }
}