package fr.isen.lan.androiderestaurant

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.lan.androiderestaurant.databinding.ActivityUserBinding

/**
 * Display information about a user
 */
class UserActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userLogoutButton.setOnClickListener {
            this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).edit().remove(getString(R.string.spUserId)).apply()
            Toast.makeText(this, getString(R.string.successLogout), Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}