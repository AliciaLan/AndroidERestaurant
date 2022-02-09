package fr.isen.lan.androiderestaurant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fr.isen.lan.androiderestaurant.databinding.ActivityLoginBinding

const val ID = "id_user"

class LoginActivity : MenuActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.loginFragmentContainerView.id, SignUpFragment()).commit()
    }

    fun loginToSignup() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.loginFragmentContainerView.id, SignUpFragment()).commit()
    }

    fun signupToLogin() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.loginFragmentContainerView.id, LoginFragment()).commit()
    }

    fun loginToCommand(id : Int) {
        val intent = Intent(this, CommandActivity::class.java).apply {
            putExtra(ID, id)
        }
        startActivity(intent)
    }
}