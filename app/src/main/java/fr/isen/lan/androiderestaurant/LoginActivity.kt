package fr.isen.lan.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fr.isen.lan.androiderestaurant.databinding.ActivityLoginBinding
import fr.isen.lan.androiderestaurant.fragment.LoginFragment
import fr.isen.lan.androiderestaurant.fragment.SignUpFragment

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
        this.getSharedPreferences(getString(R.string.spFileName), Context.MODE_PRIVATE).edit().putInt(getString(R.string.spUserId), id).apply()
        startActivity(Intent(this, CommandActivity::class.java))
    }
}