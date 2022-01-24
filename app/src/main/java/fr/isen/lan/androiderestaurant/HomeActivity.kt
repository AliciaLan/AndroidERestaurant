package fr.isen.lan.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun clickEntree(v : View) {
        createToast("Entrée")
    }


    fun clickPlat(v : View) {
        createToast("Plat")
    }


    fun clickDessert(v : View) {
        createToast("Dessert")
    }


    fun createToast (txt : String) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
    }
}