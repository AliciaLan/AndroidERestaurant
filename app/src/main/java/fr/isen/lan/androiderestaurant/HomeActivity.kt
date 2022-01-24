package fr.isen.lan.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast


val TITLE_CATEGORY = "title"

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun clickEntree(v : View) {
        createToast("Entrée")
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra(TITLE_CATEGORY, getString(R.string.entree))
        }
        startActivity(intent)
        Log.d("LOG", "HomeActivity stop")
    }

    fun clickPlat(v : View) {
        createToast("Plat")
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra(TITLE_CATEGORY, getString(R.string.plat))
        }
        startActivity(intent)
        Log.d("LOG", "HomeActivity stop")
    }

    fun clickDessert(v : View) {
        createToast("Dessert")
        val intent = Intent(this, CategoryActivity::class.java).apply {
            putExtra(TITLE_CATEGORY, getString(R.string.dessert))
        }
        startActivity(intent)
        Log.d("LOG", "HomeActivity stop")
    }

    private fun createToast (txt : String) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
    }
}